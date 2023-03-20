/*
 * Copyright (c) 2023 Tim Vaughan
 *
 * This file is part of remaster.
 *
 * remaster is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * remaster is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with remaster. If not, see <https://www.gnu.org/licenses/>.
 */

package remaster;

import beast.base.core.Input;
import beast.base.core.Log;
import beast.base.evolution.tree.Node;
import beast.base.util.Randomizer;

import java.io.PrintStream;
import java.util.*;

public class StochasticTrajectory extends AbstractBDTrajectory {

    public Input<Integer> maxRetriesInput = new Input<>("maxRetries",
            "Maximum number of times to retry simulation after failure " +
                    "to meet mustHave condition.", 100);

    List<BDTrajectoryEvent> events;

    @Override
    public void initAndValidate() {
        super.initAndValidate();

        events = new ArrayList<>();

        int retries = maxRetriesInput.get();
        while (retries>=0 && !doSimulation()) {
            retries -= 1;
            if (retries >=0)
                Log.info("Trajectory simulation rejected: retrying");
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
                    double multiplicity = state.implementEvent((PunctualReaction) updatedReaction, true);
                    if (multiplicity>0)
                        events.add(new BDTrajectoryEvent(t, updatedReaction, multiplicity));
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

            events.add(new BDTrajectoryEvent(t, thisReaction, 1));
            state.incrementState(thisReaction, 1);

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
        if (events.stream().noneMatch(e -> state.producesSamples(e.reaction)))
            throw new SimulationFailureException("No samples produced.");

        List<BDTrajectoryEvent> eventList = new ArrayList<>(events);
        Collections.reverse(eventList);

        Map<ReactElement, List<Lineage>> lineages = new HashMap<>();

        state.resetToFinal();

        LineageFactory lineageFactory = new LineageFactory();

        for (BDTrajectoryEvent event : eventList) {
            for (long i=0; i<Math.round(event.multiplicity); i++) {
                state.incrementLineages(lineages, event.reaction,
                        event.time, lineageFactory, false);
                state.incrementState(event.reaction, -1);
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

        for (BDTrajectoryEvent event : events) {
            out.print(";");
            out.print("t=" + event.time);
            state.incrementState(event.reaction, event.multiplicity);
            out.print(state);
        }

        out.print("\t");
    }
}
