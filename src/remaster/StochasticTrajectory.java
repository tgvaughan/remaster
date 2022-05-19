package remaster;

import beast.core.BEASTObject;
import beast.core.Function;
import beast.core.Input;
import beast.core.Loggable;
import beast.core.parameter.RealParameter;
import beast.core.util.Log;
import beast.util.Randomizer;

import java.io.PrintStream;
import java.util.*;

public class StochasticTrajectory extends BEASTObject implements Loggable {

    public Input<List<Function>> populationsInput = new Input<>("population",
            "Population or compartment", new ArrayList<>());

    public Input<List<Function>> samplePopulationsInput = new Input<>("samplePopulation",
            "Sample population or compartment", new ArrayList<>());

    public Input<List<AbstractReaction>> reactionsInput = new Input<>("reaction",
            "Reaction", new ArrayList<>());

    public Input<Function> endTimeInput = new Input<>("endTime",
            "Period of simulation", new RealParameter("Infinity"));

    TrajectoryState state;
    List<AbstractReaction> reactions;
    List<Reaction> continuousReactions;
    List<PunctualReaction> punctualReactions;

    List<TrajectoryEvent> events;

    @Override
    public void initAndValidate() {
        state = new TrajectoryState(populationsInput.get(), samplePopulationsInput.get());
        events = new ArrayList<>();

        reactions = reactionsInput.get();
        continuousReactions = new ArrayList<>();
        punctualReactions = new ArrayList<>();

        for (AbstractReaction reaction : reactions) {
            reaction.markSamples(state);
            if (!reaction.isValid(state))
                throw new IllegalStateException("Invalid reaction detected.");

            if (reaction instanceof Reaction)
                continuousReactions.add((Reaction)reaction);
            else if (reaction instanceof PunctualReaction)
                punctualReactions.add((PunctualReaction)reaction);
            else
                throw new IllegalArgumentException("Unsupported reaction type: " +
                        reaction.getClass().getCanonicalName());
        }

        while (!doSimulation())
            Log.info("Simulation rejected: retrying");
    }


    public boolean doSimulation() {
        state.resetToInitial();
        events.clear();

        for (AbstractReaction reaction : reactions)
            reaction.reset();

        List<AbstractReaction> reactionsSortedByChangeTimes = new ArrayList<>(reactions);
        reactionsSortedByChangeTimes.sort(Comparator.comparingDouble(AbstractReaction::getIntervalEndTime));

        double t=0.0;

        while (true) {
            double a0 = 0.0;
            for (Reaction reaction : continuousReactions)
                a0 += reaction.updatePropensity(state);

            double delta = a0 == 0 ? Double.POSITIVE_INFINITY : Randomizer.nextExponential(a0);
            t += delta;

            AbstractReaction updatedReaction = reactionsSortedByChangeTimes.get(0);
            if (t > updatedReaction.getIntervalEndTime()) {
                t = updatedReaction.getIntervalEndTime();

                if (updatedReaction instanceof PunctualReaction) {
                    // Implement punctual reaction
                    double multiplicity = ((PunctualReaction) updatedReaction).implementEvent(state);
                    events.add(new TrajectoryEvent(t, updatedReaction, multiplicity));
                }

                updatedReaction.incrementInterval();
                reactionsSortedByChangeTimes
                        .sort(Comparator.comparingDouble(AbstractReaction::getIntervalEndTime));

                if (!state.isValid())
                    return false;

                continue;
            }

            if (delta == Double.POSITIVE_INFINITY)
                break;

            double u = Randomizer.nextDouble()*a0;

            Reaction thisReaction = null;
            for (Reaction reaction : continuousReactions) {
                if (u < reaction.currentPropensity) {
                    thisReaction = reaction;
                    break;
                } else
                    u -= reaction.currentPropensity;
            }

            if (thisReaction == null)
                throw new IllegalStateException("Reaction selection loop fell through.");

            events.add(new TrajectoryEvent(t, thisReaction, 1));
            thisReaction.incrementState(state, 1);
        }

        state.setFinal();

        return true;
    }

    @Override
    public void init(PrintStream out) {
        if (getID() != null)
            out.print(getID() + "\t");
    }

    @Override
    public void log(long sample, PrintStream out) {
        state.resetToInitial();

        out.print("t=0");
        out.print(state);

        for (TrajectoryEvent event : events) {
            out.print(";");
            out.print("t=" + event.time);
            event.reaction.incrementState(state, event.multiplicity);
            out.print(state);
        }

        out.print("\t");
    }

    @Override
    public void close(PrintStream out) { }

}
