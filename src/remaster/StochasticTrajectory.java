package remaster;

import beast.core.BEASTObject;
import beast.core.Function;
import beast.core.Input;
import beast.core.Loggable;
import beast.core.parameter.RealParameter;
import beast.util.Randomizer;
import com.google.common.collect.SortedMultiset;
import com.google.common.collect.TreeMultiset;

import java.io.PrintStream;
import java.util.*;

public class StochasticTrajectory extends BEASTObject implements Loggable {

    public Input<List<Function>> populationsInput = new Input<>("population",
            "Population or compartment", new ArrayList<>());

    public Input<List<Function>> samplePopulationsInput = new Input<>("samplePopulation",
            "Sample population or compartment", new ArrayList<>());

    public Input<List<Reaction>> reactionsInput = new Input<>("reaction",
            "Reaction", new ArrayList<>());

    public Input<Function> endTimeInput = new Input<>("endTime",
            "Period of simulation", new RealParameter("Infinity"));

    TrajectoryState state;
    List<Reaction> reactions;

    List<TrajectoryEvent> events;

    @Override
    public void initAndValidate() {
        state = new TrajectoryState(populationsInput.get(), samplePopulationsInput.get());
        events = new ArrayList<>();

        reactions = reactionsInput.get();

        for (Reaction reaction : reactions) {
            reaction.markSamples(state);
            if (!reaction.isValid(state))
                throw new IllegalStateException("Invalid reaction detected.");
        }

        doSimulation();
    }


    public void doSimulation() {
        state.resetToInitial();
        events.clear();

        List<Reaction> reactionsSortedByChangeTimes = new ArrayList<>(reactions);
        reactionsSortedByChangeTimes.sort(Comparator.comparingDouble(Reaction::getNextChangeTime));

        double t=0.0;

        while (true) {
            double a0 = 0.0;
            for (Reaction reaction : reactions)
                a0 += reaction.updatePropensity(state);

            double delta = a0 == 0 ? Double.POSITIVE_INFINITY : Randomizer.nextExponential(a0);
            t += delta;

            Reaction updatedReaction = reactionsSortedByChangeTimes.get(0);
            if (t > updatedReaction.getNextChangeTime()) {
                t = updatedReaction.getNextChangeTime();

                updatedReaction.incrementInterval();
                reactionsSortedByChangeTimes.sort(Comparator.comparingDouble(Reaction::getNextChangeTime));

                continue;
            }

            if (delta == Double.POSITIVE_INFINITY)
                break;

            double u = Randomizer.nextDouble()*a0;

            Reaction thisReaction = null;
            for (Reaction reaction : reactions) {
                if (u < reaction.currentPropensity) {
                    thisReaction = reaction;
                    break;
                } else
                    u -= reaction.currentPropensity;
            }

            if (thisReaction == null)
                throw new IllegalStateException("Reaction selection loop fell through.");

            events.add(new TrajectoryEvent(t, thisReaction));
            thisReaction.incrementState(state);
        }

        state.setFinal();
    }

    @Override
    public void init(PrintStream out) {
        if (getID() != null)
            out.print(getID() + "\t");
    }

    @Override
    public void log(long sample, PrintStream out) {
        state.resetToInitial();

        boolean isFirst = true;
        for (TrajectoryEvent event : events) {
            if (isFirst) {
                isFirst = false;
                out.print("t=0");
            } else {
                out.print(";");
                out.print("t=" + event.time);
            }

            out.print(state);
            event.reaction.incrementState(state);
        }

        out.print("\t");
    }

    @Override
    public void close(PrintStream out) { }

    /**
     * Events produced by stochastic trajectories.
     */
    public static class TrajectoryEvent {
        public double time;
        Reaction reaction;

        public TrajectoryEvent(double time, Reaction reaction) {
            this.time = time;
            this.reaction = reaction;
        }
    }
}
