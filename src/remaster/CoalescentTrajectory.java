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

import java.io.PrintStream;
import java.util.*;

public class CoalescentTrajectory extends AbstractTrajectory {

    public Input<List<PopulationFunction.Abstract>> popFuncInput = new Input<>("population",
        "Population represented by a beast PopulationFunction object",
        new ArrayList<>());

    Set<ReactElement> popElements;
    List<ContinuousCoalescentReaction> continousCoalReactions;
    List<PunctualCoalescentReaction> punctualCoalReactions;

    @Override
    public void initAndValidate() {
        super.initAndValidate();

        popElements = new HashSet<>();
        for (PopulationFunction.Abstract popFunc : popFuncInput.get())
            popElements.add(new ReactElement(popFunc.getID(), 0));

        continousCoalReactions = new ArrayList<>();
        for (PopulationFunction.Abstract popFunc : popFuncInput.get())
            continousCoalReactions.add(new ContinuousCoalescentReaction(popFunc));
        for (Reaction reaction : continuousReactions)
            continousCoalReactions.add(new ContinuousCoalescentReaction(reaction, popElements));

        punctualCoalReactions = new ArrayList<>();
        for (PunctualReaction reaction : punctualReactions)
            punctualCoalReactions.add(new PunctualCoalescentReaction(reaction, popElements));
    }

    @Override
    public Node simulateTree() {

        for (AbstractReaction reaction : reactions)
            reaction.resetInterval();

        LineageFactory lineageFactory = new LineageFactory();
        Map<ReactElement, List<Lineage>> lineages = new HashMap<>();
        for (ReactElement popEl : popElements)
            lineages.put(popEl, new ArrayList<>());

        List<PunctualCoalescentReaction> punctualReactionsByChangeTime = new ArrayList<>(punctualCoalReactions);
        punctualReactionsByChangeTime.sort(Comparator.comparingDouble(PunctualCoalescentReaction::getReactionTime));

        double t = 0.0;

        while (true) {

            // Sample time increment

            double nextReactionTime = Double.POSITIVE_INFINITY;
            ContinuousCoalescentReaction nextReaction = null;

            boolean leavesToCome = false;

            for (ContinuousCoalescentReaction reaction : continousCoalReactions) {
                double thisReactionTime = reaction.getNextReactionTime(t, lineages);
                if (thisReactionTime < nextReactionTime) {
                    nextReactionTime = thisReactionTime;
                    nextReaction = reaction;
                }

                if (reaction.generatesLeaves && thisReactionTime < Double.POSITIVE_INFINITY)
                    leavesToCome = true;
            }

            if (nextReactionTime > punctualReactionsByChangeTime.get(0).getReactionTime()) {
                t = punctualReactionsByChangeTime.get(0).getReactionTime();
                PunctualCoalescentReaction punctualReaction = punctualReactionsByChangeTime.get(0);
                punctualReaction.applyReaction(lineages, lineageFactory);
                punctualReactionsByChangeTime.sort(Comparator.comparingDouble(PunctualCoalescentReaction::getReactionTime));
                continue;
            }

            if (nextReaction == null
                    || (lineages.values().stream().mapToInt(List::size).sum() == 1)
                    && !leavesToCome)
                break;

            // Select and implement reaction
            nextReaction.applyReaction(nextReactionTime, lineages, lineageFactory);

            t = nextReactionTime;
        }

        List<Lineage> rootLineages = new ArrayList<>();
        lineages.forEach((el,lineageList) -> rootLineages.addAll(lineageList));

        if (rootLineages.size() != 1)
            throw new IllegalStateException("Coalescent simulation terminated " +
                    "with more or less than 1 lineage.");

        lineageFactory.numberInternals(rootLineages.get(0));

        return rootLineages.get(0);
    }

    @Override
    public void log(long sample, PrintStream out) {
        // TODO
        out.println("NA\t");
    }

}
