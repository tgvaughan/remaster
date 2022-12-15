package remaster;

import beast.base.core.Input;
import beast.base.core.Log;
import beast.base.evolution.tree.Node;
import beast.base.util.Randomizer;

import java.io.PrintStream;
import java.util.*;

public class StochasticTrajectory extends AbstractTrajectory {

    public Input<Integer> maxRetriesInput = new Input<>("maxRetries",
            "Maximum number of times to retry simulation after failure " +
                    "to meet mustHave condition.", 100);

    List<TrajectoryEvent> events;

    @Override
    public void initAndValidate() {
        super.initAndValidate();

        events = new ArrayList<>();

        int retries = maxRetriesInput.get();
        while (retries>=0 && !doSimulation()) {
            retries -= 1;
            if (retries >=0)
                Log.info("Simulation rejected: retrying");
            else
                Log.err.println("Failed to simulate trajectory satisfying " +
                        "mustHave condition. (maxRetires = " +
                        maxTimeInput.get() + ")");
        }
    }


    @Override
    public boolean doSimulation() {
        state.resetToInitial();
        events.clear();

        for (AbstractReaction reaction : reactions)
            reaction.resetInterval();

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
                    double multiplicity = ((PunctualReaction) updatedReaction).implementEvent(state, true);
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

            if (endCondition != null && endCondition.isMet()) {
                System.out.println("Trajectory termination condition met: " + endsWhenInput.get());
                break;
            }
        }

        if (acceptCondition != null && !acceptCondition.isMet()) {
            System.out.println("Trajectory acceptance condition not met: " + mustHaveInput.get());
            return false;
        }

        state.setFinal();
        return true;
    }

    @Override
    public Node simulateTree() throws SimulationFailureException {
        if (events.stream().noneMatch(e -> e.reaction.producesSamples))
            throw new SimulationFailureException("No samples produced.");

        List<TrajectoryEvent> eventList = new ArrayList<>(events);
        Collections.reverse(eventList);

        Map<ReactElement, List<Lineage>> lineages = new HashMap<>();

        state.resetToFinal();

        LineageFactory lineageFactory = new LineageFactory();

        for (TrajectoryEvent event : eventList) {
            for (long i=0; i<Math.round(event.multiplicity); i++) {
                event.reaction.incrementLineages(lineages, state,
                        event.time, lineageFactory, false);
                event.reaction.reverseIncremementState(state, 1);
            }
        }

        List<Lineage> rootLineages = new ArrayList<>();
        for (ReactElement pop : lineages.keySet())
            rootLineages.addAll(lineages.get(pop));

        // Restrict reactions to ensure this is impossible
        if (rootLineages.isEmpty())
            throw new SimulationFailureException("No lineages remaining.");

        // Might allow this in future
        if (rootLineages.size()>1)
            throw new SimulationFailureException("Multiple lineages remaining.");

        lineageFactory.numberInternals(rootLineages.get(0));

        return rootLineages.get(0);
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
