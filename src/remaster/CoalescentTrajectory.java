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
import beast.base.evolution.tree.Node;
import beast.base.evolution.tree.coalescent.PopulationFunction;
import beast.base.util.Binomial;
import beast.base.util.Randomizer;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.io.PrintStream;
import java.util.*;

public class CoalescentTrajectory extends AbstractTrajectory {

    public Input<List<PopulationFunction.Abstract>> popFuncInput = new Input<>("population",
        "Population represented by a beast PopulationFunction object",
        new ArrayList<>());

    Set<ReactElement> popElements;
    List<CoalescentReaction> coalReactions;

    @Override
    public void initAndValidate() {
        popElements = new HashSet<>();
        for (PopulationFunction.Abstract popFunc : popFuncInput.get())
            popElements.add(new ReactElement(popFunc.getID(), 0));

        coalReactions = new ArrayList<>();
        for (PopulationFunction.Abstract popFunc : popFuncInput.get())
            coalReactions.add(new CoalescentReaction(popFunc));
        for (Reaction reaction : continuousReactions)
            coalReactions.add(new CoalescentReaction(reaction, popElements));
    }

    @Override
    public Node simulateTree() throws SimulationFailureException {

        for (AbstractReaction reaction : reactions)
            reaction.resetInterval();

        LineageFactory lineageFactory = new LineageFactory();
        Map<ReactElement, List<Lineage>> lineages = new HashMap<>();

        double tEnd = maxTimeInput.get() != null
                ? maxTimeInput.get().getArrayValue()
                : Double.POSITIVE_INFINITY;

        List<PunctualReaction> punctualReactionsByChangeTime = new ArrayList<>(punctualReactions);
        punctualReactionsByChangeTime.sort(Comparator.comparingDouble(AbstractReaction::getIntervalEndTime));

        double t = 0.0;
        while (t < tEnd) {

            // Sample time increment

            double nextReactionTime = Double.POSITIVE_INFINITY;
            CoalescentReaction nextReaction = null;

            for (CoalescentReaction reaction : coalReactions) {
                double thisReactionTime = reaction.getNextReactionTime(t, lineages);
                if (thisReactionTime < nextReactionTime) {
                    nextReactionTime = thisReactionTime;
                    nextReaction = reaction;
                }
            }

            if (nextReactionTime > punctualReactionsByChangeTime.get(0).getIntervalEndTime()) {
                PunctualReaction punctualReaction = punctualReactionsByChangeTime.get(0);

                punctualReaction.incrementInterval();
                continue;
            }

            if (nextReaction == null)
                break;

            // Select and implement reaction
            nextReaction.incrementLineages(nextReactionTime, lineages, lineageFactory);

            t = nextReactionTime;
        }

        List<Lineage> flattenedLineages = new ArrayList<>();
        lineages.forEach((el,lineageList) -> flattenedLineages.addAll(lineageList));

        if (lineages.size() != 1)
            throw new IllegalStateException("Coalescent simulation terminated " +
                    "with more or less than 1 lineage. (Likely an internal error.)");

        return flattenedLineages.get(0);
    }


    static class CoalescentReaction {
        PopulationFunction popFunc;
        Reaction reaction;

        ReactElement popEl;

        List<ReactElement> parents = new ArrayList<>();
        List<Multiset<ReactElement>> children = new ArrayList<>();

        public CoalescentReaction(PopulationFunction.Abstract popFunc) {
            this.popFunc = popFunc;
            this.popEl = new ReactElement(popFunc.getID(), 0);
        }

        public CoalescentReaction(Reaction reaction, Set<ReactElement> popElements) {
            this.reaction = reaction;

            Map<String, Integer> parentIDs = new HashMap<>();

            // Process products

            for (int i=0; i<reaction.productList.size(); i++) {
                ReactElement el = reaction.productList.get(i);
                if (!popElements.contains(el))
                    throw new IllegalArgumentException("Population '" + el.toString() + "' unknown.");

                parents.add(el);
                children.add(HashMultiset.create());
                String prodID = reaction.productIDList.get(i);
                if (prodID != null) {
                    if (parentIDs.containsKey(prodID))
                        throw new IllegalArgumentException("Products cannot share an ID.");
                    parentIDs.put(prodID, i);
                }
            }

            // Process reactants:

            for (int i=0; i<reaction.reactantList.size(); i++) {
                ReactElement el = reaction.reactantList.get(i);
                if (!popElements.contains(el))
                    throw new IllegalArgumentException("Population '" + el.toString() + "' unknown.");

                int parentIndex = 0;

                String reactID = reaction.reactantIDList.get(i);
                if (reactID != null) {
                    if (!parentIDs.containsKey(reactID))
                        throw new IllegalArgumentException("The reaction ID '" + reactID +
                                "' is not associated with a product.");
                    parentIndex = parentIDs.get(reactID);
                } else if (parents.stream().anyMatch(p -> p.name.equals(el.name))) {
                    while (parentIndex < parents.size() && !parents.get(parentIndex).name.equals(el.name))
                        parentIndex += 1;
                }

                if (parentIndex < parents.size())
                    children.get(parentIndex).add(el);
            }
        }

        public double getNextReactionTime( double currentTime,
                                          Map<ReactElement, List<Lineage>> lineages) {
            double u = Randomizer.nextDouble();

            if (popFunc != null) {
                return PopulationFunction.Utils.getSimulatedInterval(popFunc,
                        lineages.get(popEl).size(), currentTime);
            } else {
                double prop = getPropensity(lineages);
                double dt = -Math.log(u)/prop;
                double t = currentTime;
                while (t+dt > reaction.getIntervalEndTime()) {
                    u -= Math.exp(-(t - reaction.getIntervalEndTime()) * prop);
                    t = reaction.getIntervalEndTime();
                    reaction.incrementInterval();
                    prop = getPropensity(lineages);
                    dt = -Math.log(u)/prop;
                }

                return t + dt;
            }
        }

        private double getPropensity(Map<ReactElement, List<Lineage>> lineages) {
            double propensity = reaction.getIntervalRate();

            for (ReactElement el : reaction.reactants.elementSet())
                propensity *= Binomial.choose(lineages.get(el).size(),
                        reaction.reactants.count(el));

            return propensity;
        }

        private final Multiset<ReactElement> seenElements = HashMultiset.create();

        public void incrementLineages(double nextReactionTime,
                                      Map<ReactElement, List<Lineage>> lineages,
                                      LineageFactory lineageFactory) {

            for (int i=0; i<children.size(); i++) {
                ReactElement parentEl = parents.get(i);

                Lineage parent = children.get(i).isEmpty()
                        ? lineageFactory.createSample(parentEl, nextReactionTime)
                        : lineageFactory.createInternal(parentEl, nextReactionTime);

                for (ReactElement childEl : children.get(i)) {
                    List<Lineage> elementLineages = lineages.get(childEl);
                    parent.addChild(elementLineages.remove(Randomizer.nextInt(elementLineages.size())));
                }

                lineages.get(parentEl).add(parent);
            }
        }
    }

    @Override
    public void log(long sample, PrintStream out) {
        // TODO
    }

}
