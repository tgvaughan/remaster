package remaster;

import beast.core.util.Log;
import beast.util.Randomizer;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StochasticTrajectory extends AbstractTrajectory {

    List<TrajectoryEvent> events;

    @Override
    public void initAndValidate() {
        super.initAndValidate();

        events = new ArrayList<>();

        while (!doSimulation())
            Log.info("Simulation rejected: retrying");
    }


    @Override
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
            if (maxTimeInput.get().getArrayValue() < updatedReaction.getIntervalEndTime()) {
                if (t > maxTimeInput.get().getArrayValue())
                    break;
            } else if (t > updatedReaction.getIntervalEndTime()) {
                t = updatedReaction.getIntervalEndTime();

                if (updatedReaction instanceof PunctualReaction) {
                    // Implement punctual reaction
                    double multiplicity = ((PunctualReaction) updatedReaction).implementEvent(state);
                    if (multiplicity>0)
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

            if (endCondition != null && endCondition.isMet())
                break;
        }

        if (endCondition != null && !endCondition.isMet())
            return false;

        state.setFinal();
        return true;
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
}
