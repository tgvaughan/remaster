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

package remaster.reactionboxes;

import beast.base.util.Randomizer;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import remaster.*;

import java.util.*;

/**
 * Wrapper class for birth-death reactions.
 */
public abstract class BDReactionBox {

    BDTrajectoryState state;

    List<ReactElement> parents = new ArrayList<>();
    List<Multiset<ReactElement>> children = new ArrayList<>();

    AbstractReaction reaction;
    Set<String> samplePopNames;

    public boolean producesSamples;

    public double[] stoichiometryVector;

    public BDReactionBox(AbstractReaction reaction, Set<String> samplePopNames, BDTrajectoryState state) {
        this.reaction = reaction;
        this.samplePopNames = samplePopNames;
        this.state = state;

        Map<String, Integer> parentIDs = new HashMap<>();

        for (int i=0; i<reaction.reactantList.size(); i++) {
            ReactElement el  = reaction.reactantList.get(i);
            parents.add(el);
            children.add(HashMultiset.create());

            String reactID = reaction.reactantIDList.get(i);
            if (reactID != null) {
                if (parentIDs.containsKey(reactID))
                    throw new IllegalStateException("In reaction '" +
                            reaction.reactionStringInput.get() +
                            "' reactants cannot share an ID.");
                parentIDs.put(reactID, i);
            }

            // Hack: mark scalar reaction elements as such:
            if (state.popDims.get(el.name)==1)
                el.isScalar = true;
        }

        for (int i=0; i<reaction.productList.size(); i++) {
            int parentIndex = 0;
            ReactElement el = reaction.productList.get(i);

            String prodID = reaction.productIDList.get(i);
            if (prodID != null) {
                if (!parentIDs.containsKey(prodID))
                    throw new IllegalStateException("In reaction '" +
                            reaction.reactionStringInput.get() +
                            "' the product ID " + "'" + prodID +
                            "' is not associated with a reactant.");
                parentIndex = parentIDs.get(prodID);
            } else if (parents.stream().anyMatch(p -> p.name.equals(el.name))) {
                // Find first parent with same population
                while (parentIndex < parents.size() && !parents.get(parentIndex).name.equals(el.name))
                    parentIndex += 1;
            }

            if (parentIndex < parents.size())
                children.get(parentIndex).add(el);


            // Hack: mark scalar reaction elements as such:
            if (state.popDims.get(el.name)==1)
                el.isScalar = true;
        }

        for (ReactElement element : reaction.products.elementSet()) {
            if (samplePopNames.contains(element.name)) {
                producesSamples = true;
                break;
            }
        }

        for (ReactElement reactElement : Sets.union(reaction.reactants.elementSet(),
                reaction.products.elementSet())) {
            if (!state.hasPopNamed(reactElement.name)) {
                throw new IllegalArgumentException(
                        "Reaction " + reaction.getID() + " (" + reaction +
                        ") contains unknown element '" + reactElement.name + "'.");
            }
        }

        for (ReactElement reactElement : reaction.reactants.elementSet()) {
            if (samplePopNames.contains(reactElement.name)) {
                throw new IllegalArgumentException(
                        "Reaction " + reaction.getID() + " (" + reaction
                        + ") contains sample population '" + reactElement.name
                        + "' as reactant.");
            }
        }

        // Compute stoichiometry vector (used for deterministic trajectories)
        stoichiometryVector = new double[state.getTotalSubpopCount()];
        for (ReactElement reactElement : reaction.reactants)
            stoichiometryVector[state.getOffset(reactElement)] -= 1;

        for (ReactElement reactElement : reaction.products) {
            stoichiometryVector[state.getOffset(reactElement)] += 1;
        }
    }

    /**
     * Increment state according to reaction
     *
     * @param state birth-death state to increment
     * @param multiplicity number of times for reaction to fire (may be fractional)
     */
    public void incrementState(BDTrajectoryState state, double multiplicity) {
        for (ReactElement reactElement : reaction.reactants.elementSet())
            state.incrementOccupancy(reactElement.name, reactElement.idx,
                    -multiplicity*reaction.reactants.count(reactElement));

        for (ReactElement reactElement : reaction.products.elementSet())
            state.incrementOccupancy(reactElement.name, reactElement.idx,
                    multiplicity*reaction.products.count(reactElement));
    }

    /**
     * Hashmap used to track how many of each type of product have already been "seen"
     * when incrementing the lineage state.
     *
     * We make this a field to avoid having to create a new hashmap every time incrementLineages()
     * is called.
     */
    private final Map<ReactElement, Integer> seenElements = new HashMap<>();

    /**
     * List used to keep track of lineages to include in a particular lineage update event.
     *
     * We make this a field to avoid having to create a new arraylist every time incrementLineages()
     * is called.
     */
    private final List<Lineage> toInclude = new ArrayList<>();

    /**
     * Increment lineage state according to reaction.
     *
     * @param lineages current extant lineage population
     * @param eventTime time of reaction
     * @param lineageFactory factory object for creating new lineages
     * @param conditionOnInclusion increment lineages conditional on at least
     *                             one sampled lineage being involved in the
     *                             reaction.
     */
    public void incrementLineages(Map<ReactElement, List<Lineage>> lineages, double eventTime,
                                  LineageFactory lineageFactory,
                                  boolean conditionOnInclusion) throws AbstractTrajectory.SimulationFailureException {
        if (lineages.isEmpty() && !producesSamples) {
            if (conditionOnInclusion)
                throw new AbstractTrajectory.SimulationFailureException("incrementLineages: " +
                        "conditionOnInclusion is true, but no lineages remain. " +
                        "(Try reducing the integration step size.)");
            return;
        }

        // Iterate over **products**

        double u = Randomizer.nextDouble();

        double totalInclusionProb = 1.0;
        if (conditionOnInclusion) {
            totalInclusionProb = getLineageInclusionProbability(lineages);
            if (totalInclusionProb == 0)
                throw new AbstractTrajectory.SimulationFailureException("incrementLineages: " +
                        "conditionOnInclusion is true, but totalInclusionProb " +
                        "is zero. (Try reducing the integration step size.)");
        }

        for (int i=0; i<children.size(); i++) {
            for (ReactElement el : children.get(i)) {
                if (!seenElements.containsKey(el))
                    seenElements.put(el, 0);

                if (samplePopNames.contains(el.name)) {
                    toInclude.add(lineageFactory.createSample(parents.get(i), el, eventTime));
                    conditionOnInclusion = false;
                    totalInclusionProb = 1.0;
                    continue;
                }

                double pInclude = lineages.containsKey(el)
                        ? lineages.get(el).size()/(state.get(el) - seenElements.get(el))
                        : 0.0;

                if (u*totalInclusionProb < pInclude) {
                    int lineageIdx = (int)Math.round(Math.floor(lineages.get(el).size()
                            * (u*totalInclusionProb/pInclude)));

                    toInclude.add(lineages.get(el).remove(lineageIdx));

                    u = Randomizer.nextDouble();
                    conditionOnInclusion = false;
                    totalInclusionProb = 1.0;
                } else if (pInclude>0.0) {
                    u = (u*totalInclusionProb - pInclude) / (totalInclusionProb - pInclude);

                    if (conditionOnInclusion)
                        totalInclusionProb = (totalInclusionProb - pInclude)/(1 - pInclude);
                }

                seenElements.put(el, seenElements.get(el)+1);
            }

            if (toInclude.isEmpty())
                continue;

            Lineage parent;
            if (toInclude.size()==1 && toInclude.get(0).reactElement.equals(parents.get(i))) {
                parent = toInclude.get(0);
            } else {
                parent = lineageFactory.createInternal(parents.get(i), eventTime);
                for (Lineage child : toInclude) {
                    parent.addChild(child);
                }
            }

            if (!lineages.containsKey(parent.reactElement))
                lineages.put(parent.reactElement, new ArrayList<>());
            lineages.get(parent.reactElement).add(parent);

            toInclude.clear();
        }

        seenElements.clear();
    }

    /**
     * Compute the probability that a reaction is included in the tree.
     *
     * @param lineages current extant lineage state
     * @return probability that reaction affects tree
     */
    public double getLineageInclusionProbability(Map<ReactElement, List<Lineage>> lineages) {
        if (lineages.isEmpty() && !producesSamples)
            return 0;

        if (producesSamples)
            return 1.0;

        // Iterate over **products**

        double pNoInclude = 1.0;

        for (int i=0; i<children.size(); i++) {
            for (ReactElement el : children.get(i)) {
                if (!seenElements.containsKey(el))
                    seenElements.put(el, 0);

                double pInclude = lineages.containsKey(el)
                        ? lineages.get(el).size()/(state.get(el) - seenElements.get(el))
                        : 0.0;

                pNoInclude *= 1.0 - pInclude;

                seenElements.put(el, seenElements.get(el)+1);
            }
        }

        seenElements.clear();

        return 1.0 - pNoInclude;
    }

    public double getIntervalEndTime() {
        return reaction.getIntervalEndTime();
    }

    public double getIntervalStartTime() {
        return reaction.getIntervalStartTime();
    }

    public void incrementInterval() {
        reaction.incrementInterval();
    }

    public void decrementInterval() {
        reaction.decrementInterval();
    }

    public void resetInterval() {
        reaction.resetInterval();
    }

    public void resetIntervalToEnd() {
        reaction.resetIntervalToEnd();
    }

    public double[] getAllIntervalEndTimes() {
        return reaction.getAllIntervalEndTimes();
    }
}
