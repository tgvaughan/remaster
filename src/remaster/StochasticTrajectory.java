package remaster;

import beast.core.BEASTObject;
import beast.core.Function;
import beast.core.Input;
import beast.core.Loggable;
import beast.util.Randomizer;

import java.io.PrintStream;
import java.util.*;

public class StochasticTrajectory extends BEASTObject implements Loggable {

    public Input<List<Function>> populationsInput = new Input<>("population",
            "Population or compartment", new ArrayList<>());

    public Input<List<Function>> samplePopulationsInput = new Input<>("samplePopulation",
            "Sample population or compartment", new ArrayList<>());

    public Input<List<Reaction>> reactionsInput = new Input<>("reaction",
            "Reaction", new ArrayList<>());

    TrajectoryState state;
    Set<String> samplePops;
    List<Reaction> reactions;

    List<TrajectoryEvent> events;

    @Override
    public void initAndValidate() {
        state = new TrajectoryState(populationsInput.get(), samplePopulationsInput.get());
        events = new ArrayList<>();

        reactions = reactionsInput.get();

        for (Reaction reaction : reactions) {
            if (!reaction.isValid(state))
                throw new IllegalStateException("Invalid reaction detected.");
        }


        doSimulation();
    }



    public void doSimulation() {
        state.reset();
        events.clear();

        double t=0.0;

        while (true) {
            double a0 = 0.0;
            for (Reaction reaction : reactions) {
                reaction.updatePropensity(state);
                a0 += reaction.currentPropensity;
            }

            if (a0 == 0.0)
                break;

            double delta = Randomizer.nextExponential(a0);
            t += delta;

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


    }

    @Override
    public void init(PrintStream out) {
        if (getID() != null)
            out.print(getID() + "\t");
    }


    @Override
    public void log(long sample, PrintStream out) {
        state.reset();

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
    public void close(PrintStream out) {

    }

    public static class TrajectoryEvent {
        public double time;
        Reaction reaction;

        public TrajectoryEvent(double time, Reaction reaction) {
            this.time = time;
            this.reaction = reaction;
        }
    }
}
