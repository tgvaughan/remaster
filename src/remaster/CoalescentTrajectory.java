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

import beast.base.core.Description;
import beast.base.core.Input;
import beast.base.evolution.tree.Node;
import beast.base.evolution.tree.coalescent.PopulationFunction;
import remaster.reactionboxes.ContinuousCoalescentReactionBox;
import remaster.reactionboxes.PunctualCoalescentReactionBox;

import java.io.PrintStream;
import java.util.*;

@Description("An object which represents a coalescent population trajectory. " +
        "Note that this object can be logged to produce a table of population " +
        "sizes which can be read into R for plotting.")
public class CoalescentTrajectory extends AbstractTrajectory {

    public Input<List<PopulationFunction.Abstract>> popFuncInput = new Input<>("population",
        "Population represented by a beast PopulationFunction object",
        new ArrayList<>());

    public Input<Double> maxTrajLogAgeInput = new Input<>("maxTrajLogAge",
            "Maximum age for logging population dynamics.");

    public Input<Integer> loggingGridSizeInput = new Input<>("loggingGridSize",
            "Number of evenly spaced samples used to log population dynamics.",
            101);

    Set<ReactElement> popElements;
    List<ContinuousCoalescentReactionBox> continuousCoalReactions;
    List<PunctualCoalescentReactionBox> punctualCoalReactions;

    @Override
    public void initAndValidate() {
        super.initAndValidate();

        popElements = new HashSet<>();
        for (PopulationFunction.Abstract popFunc : popFuncInput.get())
            popElements.add(new ReactElement(popFunc.getID(), 0, true));

        continuousCoalReactions = new ArrayList<>();
        for (PopulationFunction.Abstract popFunc : popFuncInput.get())
            continuousCoalReactions.add(new ContinuousCoalescentReactionBox(popFunc));
        for (Reaction reaction : continuousReactions)
            continuousCoalReactions.add(new ContinuousCoalescentReactionBox(reaction, popElements));

        punctualCoalReactions = new ArrayList<>();
        for (PunctualReaction reaction : punctualReactions)
            punctualCoalReactions.add(new PunctualCoalescentReactionBox(reaction, popElements));
    }

    @Override
    public Node simulateTree() {

        for (AbstractReaction reaction : reactions)
            reaction.resetInterval();

        LineageFactory lineageFactory = new LineageFactory();
        Map<ReactElement, List<Lineage>> lineages = new HashMap<>();
        for (ReactElement popEl : popElements)
            lineages.put(popEl, new ArrayList<>());

        List<PunctualCoalescentReactionBox> punctualReactionsByChangeTime = new ArrayList<>(punctualCoalReactions);
        punctualReactionsByChangeTime.sort(Comparator.comparingDouble(PunctualCoalescentReactionBox::getReactionTime));

        double t = 0.0;

        while (true) {

            // Sample time increment

            double nextReactionTime = Double.POSITIVE_INFINITY;
            ContinuousCoalescentReactionBox nextReaction = null;

            boolean leavesToCome = false;

            for (ContinuousCoalescentReactionBox reaction : continuousCoalReactions) {
                double thisReactionTime = reaction.getNextReactionTime(t, lineages);
                if (thisReactionTime < nextReactionTime) {
                    nextReactionTime = thisReactionTime;
                    nextReaction = reaction;
                }

                if (reaction.generatesLeaves && thisReactionTime < Double.POSITIVE_INFINITY)
                    leavesToCome = true;
            }

            if (!punctualReactionsByChangeTime.isEmpty()
                    && nextReactionTime > punctualReactionsByChangeTime.get(0).getReactionTime()) {
                t = punctualReactionsByChangeTime.get(0).getReactionTime();
                PunctualCoalescentReactionBox punctualReaction = punctualReactionsByChangeTime.get(0);
                punctualReaction.applyReaction(lineages, lineageFactory);
                punctualReactionsByChangeTime.sort(Comparator.comparingDouble(PunctualCoalescentReactionBox::getReactionTime));
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
        if (maxTrajLogAgeInput.get() == null)
            throw new IllegalArgumentException("Cannot log CoalescentTrajectory without" +
                    "specifying maxTrajLogAge.");

        double maxAge = maxTrajLogAgeInput.get();
        double sampleCount = loggingGridSizeInput.get();

        boolean isFirst = true;
        for (int i = 0; i< loggingGridSizeInput.get(); i++) {
            double t = i*maxAge/(sampleCount - 1);

            for (PopulationFunction.Abstract pop : popFuncInput.get()) {
                if (isFirst)
                    isFirst = false;
                else
                    out.print("\n" + sample + "\t");
                out.print(t + "\t");
                out.print(pop.getID() + "\t0\t");
                out.print(pop.getPopSize(t));
            }
        }

        out.print("\t");
    }

}
