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

import beast.base.core.BEASTObject;
import beast.base.core.Function;
import beast.base.core.Log;
import beast.base.util.Randomizer;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Sets;

import java.util.*;

import static remaster.Util.nextBinomial;

/**
 * Class of objects representing the state of birth death trajectories
 * (both exact and deterministic approximations)
 */
public class BDTrajectoryState {

    public double[] occupancies, initialOccupancies, finalOccupancies;

    public Map<String, Integer> popIndices = new HashMap<>();
    public Map<String, Integer> popDims = new HashMap<>();

    public Set<String> samplePopNames;

    public Set<AbstractReaction> sampleProducingReactions;

    public BDTrajectoryState(List<Function> allPops, Set<String> samplePopNames)  {

        int nextIdx = 0;

        for (Function popFunc : allPops) {
            String popName = ((BEASTObject)popFunc).getID().intern();
            popIndices.put(popName, nextIdx);
            popDims.put(popName, popFunc.getDimension());
            nextIdx += popFunc.getDimension();
        }

        occupancies = new double[nextIdx];
        initialOccupancies = new double[nextIdx];
        finalOccupancies = new double[nextIdx];

        for (Function popFunc : allPops) {
            String popName = ((BEASTObject)popFunc).getID().intern();
            System.arraycopy(popFunc.getDoubleValues(), 0, initialOccupancies,
                    popIndices.get(popName), popFunc.getDimension());
        }

        System.arraycopy(initialOccupancies, 0, occupancies, 0,
                initialOccupancies.length);

        this.samplePopNames = samplePopNames;
        sampleProducingReactions = new HashSet<>();

    }

    public Set<String> getPopNames() {
        return popIndices.keySet();
    }

    public boolean hasPopNamed(String string) {
        return popIndices.containsKey(string);
    }

    public boolean hasSamplePopNamed(String string) {
        return samplePopNames.contains(string);
    }

    public double get(String popName, int idx) {
        return occupancies[popIndices.get(popName) + idx];
    }

    public double get(ReactElement el) {
        return occupancies[popIndices.get(el.name) + el.idx];
    }

    public int getOffset(ReactElement el) {
        return popIndices.get(el.name) + el.idx;
    }

    public Double[] getArray(String popName) {
        Double[] res = new Double[popDims.get(popName)];

        int offset = popIndices.get(popName);
        for (int i=0; i<res.length; i++)
            res[i] = occupancies[offset+i];

        return res;
    }

    public int getTotalSubpopCount() {
        return occupancies.length;
    }

    public void incrementOccupancy(String popName, int idx, double amount) {
        occupancies[popIndices.get(popName)+idx] += amount;
    }

    public boolean isValid() {
        for (Double val : occupancies) {
            if (val < 0.0)
                return false;
        }

        return true;
    }

    /**
     * Performs initialisation and validation of a reaction for the
     * BD process.
     *
     * @param reaction Reaction to process
     * @return true if reaction is valid
     */
    public boolean processAndValidateReaction(AbstractReaction reaction) {

        Map<String, Integer> parentIDs = new HashMap<>();

        for (int i=0; i<reaction.reactantList.size(); i++) {
            ReactElement el  = reaction.reactantList.get(i);
            reaction.parents.add(el);
            reaction.children.add(HashMultiset.create());

            String reactID = reaction.reactantIDList.get(i);
            if (reactID != null) {
                if (parentIDs.containsKey(reactID))
                    throw new IllegalStateException("In reaction '" +
                            reaction.reactionStringInput.get() +
                            "' reactants cannot share an ID.");
                parentIDs.put(reactID, i);
            }
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
            } else if (reaction.parents.stream().anyMatch(p -> p.name.equals(el.name))) {
                // Find first parent with same population
                while (parentIndex < reaction.parents.size() && !reaction.parents.get(parentIndex).name.equals(el.name))
                    parentIndex += 1;
            }

            if (parentIndex < reaction.parents.size())
                reaction.children.get(parentIndex).add(el);
        }

        for (ReactElement element : reaction.products.elementSet()) {
            if (samplePopNames.contains(element.name)) {
                sampleProducingReactions.add(reaction);
            }
        }

        for (ReactElement reactElement : Sets.union(reaction.reactants.elementSet(),
                reaction.products.elementSet())) {
            if (!hasPopNamed(reactElement.name)) {
                Log.err("Reaction " + reaction.getID() + " (" + this +
                        ") contains unknown element '" + reactElement.name + "'.");
                return false;
            }
        }

        for (ReactElement reactElement : reaction.reactants.elementSet()) {
            if (samplePopNames.contains(reactElement.name)) {
                Log.err("Reaction " + reaction.getID() + " (" + this
                        + ") contains sample population '" + reactElement.name
                        + "' as reactant.");
                return false;
            }
        }

        return true;
    }

    public boolean producesSamples(AbstractReaction reaction) {
        return sampleProducingReactions.contains(reaction);
    }

    /**
     * Increment state according to reaction
     *
     * @param reaction
     * @param multiplicity
     */
    public void incrementState(AbstractReaction reaction, double multiplicity) {
        for (ReactElement reactElement : reaction.reactants.elementSet())
            incrementOccupancy(reactElement.name, reactElement.idx,
                    -multiplicity*reaction.reactants.count(reactElement));

        for (ReactElement reactElement : reaction.products.elementSet())
            incrementOccupancy(reactElement.name, reactElement.idx,
                    multiplicity*reaction.products.count(reactElement));
    }

    /**
     * Increment state in reverse according to reaction
     * @param reaction
     * @param multiplicity
     */
    public void reverseIncremementState(AbstractReaction reaction, double multiplicity) {
        incrementState(reaction, -multiplicity);
    }

    public double getMaxReactCount(AbstractReaction reaction) {
        double N = Double.POSITIVE_INFINITY;
        for (ReactElement el : reaction.reactants.elementSet())
            N = Math.min(Math.floor(get(el)/reaction.reactants.count(el)), N);

        return N;
    }

    public double implementEvent(PunctualReaction reaction, boolean stochastic) {
        if (reaction.isPEvent())
            return implementPEvent(reaction, stochastic);
        else
            return implementNEvent(reaction);
    }

    private double implementPEvent(PunctualReaction reaction, boolean stochastic) {
        double p = reaction.getNextP();
        double n;
        if (p == 0.0) {
            n = 0.0;
        } else {
            double N = getMaxReactCount(reaction);
            if (p == 1.0) {
                n = N;
            } else {
                if (stochastic) {
                    n =  nextBinomial(N, p);
                } else
                    n = p*N;
            }
        }

        incrementState(reaction, n);
        return n;
    }

    private double implementNEvent(PunctualReaction reaction) {
        incrementState(reaction, reaction.getNextN());
        return reaction.getNextN();
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
     * @param reaction to apply
     * @param lineages current extant lineage population
     * @param eventTime time of reaction
     * @param lineageFactory factory object for creating new lineages
     * @param conditionOnInclusion increment lineages conditional on at least
     *                             one sampled lineage being involved in the
     *                             reaction.
     */
    public void incrementLineages(Map<ReactElement, List<Lineage>> lineages, AbstractReaction reaction, double eventTime,
                                  LineageFactory lineageFactory,
                                  boolean conditionOnInclusion) {
        if (lineages.isEmpty() && !sampleProducingReactions.contains(reaction)) {
            if (conditionOnInclusion)
                throw new IllegalStateException("incrementLineages: " +
                        "conditionOnInclusion is true, but no lineages remain.");
            return;
        }

        // Iterate over **products**

        double u = Randomizer.nextDouble();

        double totalInclusionProb = 1.0;
        if (conditionOnInclusion) {
            totalInclusionProb = getLineageInclusionProbability(lineages, reaction);
            if (totalInclusionProb == 0)
                throw new IllegalStateException("incrementLineages: " +
                        "conditionOnInclusion is true, but totalInclusionProb " +
                        "is zero.");
        }

        for (int i=0; i<reaction.children.size(); i++) {
            for (ReactElement el : reaction.children.get(i)) {
                if (!seenElements.containsKey(el))
                    seenElements.put(el, 0);

                if (samplePopNames.contains(el.name)) {
                    toInclude.add(lineageFactory.createSample(reaction.parents.get(i), eventTime));
                    conditionOnInclusion = false;
                    totalInclusionProb = 1.0;
                    continue;
                }

                double pInclude = lineages.containsKey(el)
                        ? lineages.get(el).size()/(get(el) - seenElements.get(el))
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
            if (toInclude.size()==1 && toInclude.get(0).reactElement.equals(reaction.parents.get(i))) {
                parent = toInclude.get(0);
            } else {
                parent = lineageFactory.createInternal(reaction.parents.get(i), eventTime);
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
     * @param reaction corresponding reaction
     * @return probability that reaction affects tree
     */
    public double getLineageInclusionProbability(Map<ReactElement, List<Lineage>> lineages,
                                                 AbstractReaction reaction) {
        if (lineages.isEmpty() && !sampleProducingReactions.contains(reaction))
            return 0;

        if (sampleProducingReactions.contains(reaction))
            return 1.0;

        // Iterate over **products**

        double pNoInclude = 1.0;

        for (int i=0; i<reaction.children.size(); i++) {
            for (ReactElement el : reaction.children.get(i)) {
                if (!seenElements.containsKey(el))
                    seenElements.put(el, 0);

                double pInclude = lineages.containsKey(el)
                        ? lineages.get(el).size()/(get(el) - seenElements.get(el))
                        : 0.0;

                pNoInclude *= 1.0 - pInclude;

                seenElements.put(el, seenElements.get(el)+1);
            }
        }

        seenElements.clear();

        return 1.0 - pNoInclude;
    }


    public void setFinal() {
        System.arraycopy(occupancies, 0, finalOccupancies, 0,
                occupancies.length);
    }

    public void resetToInitial() {
        System.arraycopy(initialOccupancies, 0, occupancies, 0,
                occupancies.length);
    }

    public void resetToFinal() {
        System.arraycopy(finalOccupancies, 0, occupancies, 0,
                occupancies.length);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (String popName : popIndices.keySet()) {
            sb.append(":").append(popName).append("=");
            int idx = popIndices.get(popName);
            for (int i=idx; i<idx+popDims.get(popName); i++) {
                if (i>idx)
                    sb.append(",");
                sb.append(occupancies[i]);
            }
        }

        return sb.toString();
    }

}
