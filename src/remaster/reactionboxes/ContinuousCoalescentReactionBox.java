/*
 * Copyright (c) 2023 ETH Zurich
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

import beast.base.evolution.tree.coalescent.PopulationFunction;
import beast.base.util.Binomial;
import beast.base.util.Randomizer;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import remaster.Lineage;
import remaster.LineageFactory;
import remaster.ReactElement;
import remaster.Reaction;

import java.sql.ClientInfoStatus;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ContinuousCoalescentReactionBox extends CoalescentReactionBox {

    Reaction reaction;
    PopulationFunction popFunc;
    ReactElement popEl;

    public ContinuousCoalescentReactionBox(Reaction reaction, Set<ReactElement> popElements) {
        this.reaction = reaction;
        processAndValidate(reaction, popElements);
    }

    public ContinuousCoalescentReactionBox(PopulationFunction.Abstract popFunc) {
        this.popFunc = popFunc;
        popEl = new ReactElement(popFunc.getID(), 0, true);

        parents.add(popEl);

        Multiset<ReactElement> family = HashMultiset.create();
        family.add(popEl, 2);
        children.add(family);
    }

    public double getNextReactionTime( double currentTime,
                                       Map<ReactElement, List<Lineage>> lineages) {
        double u = Randomizer.nextDouble();

        if (popFunc != null) {
            return currentTime + PopulationFunction.Utils.getSimulatedInterval(
                    popFunc, lineages.get(popEl).size(), currentTime);
        } else {
            reaction.resetInterval();
            while (reaction.getIntervalEndTime()<currentTime)
                reaction.incrementInterval();

            double t = currentTime;
            // Correct inverse-CDF sampler for a piecewise-constant propensity process:
            // draw w ~ Exp(1) and solve int lambda(t) dt = w by walking intervals.
            // (The previous implementation updated u by subtracting CDF mass, which is incorrect
            //  and can yield negative u / NaN reaction times.)
            double w = -Math.log(u);
            while (true) {
                double tEnd = reaction.getIntervalEndTime();
                double prop = getPropensity(lineages);

                if (!(prop > 0.0) || Double.isNaN(prop) || Double.isInfinite(prop)) {
                    if (tEnd == Double.POSITIVE_INFINITY)
                        return Double.POSITIVE_INFINITY;
                    t = tEnd;
                    reaction.incrementInterval();
                    continue;
                }

                if (tEnd == Double.POSITIVE_INFINITY)
                    return t + w/prop;

                double dt = tEnd - t;
                if (dt < 0.0) {
                    // Numerical drift; advance.
                    t = tEnd;
                    reaction.incrementInterval();
                    continue;
                }

                double h = prop * dt;
                if (w <= h)
                    return t + w/prop;

                w -= h;
                t = tEnd;
                reaction.incrementInterval();
            }
        }
    }

    private double getPropensity(Map<ReactElement, List<Lineage>> lineages) {
        double propensity = reaction.getIntervalRate();

        for (ReactElement el : reaction.reactants.elementSet())
            propensity *= Binomial.choose(lineages.get(el).size(),
                    reaction.reactants.count(el));

        return propensity;
    }

    public void applyReaction(double nextReactionTime,
                                      Map<ReactElement, List<Lineage>> lineages,
                                      LineageFactory lineageFactory) {

            for (int i=0; i<children.size(); i++) {
                ReactElement parentEl = parents.get(i);

                Lineage parent = children.get(i).isEmpty()
                        ? lineageFactory.createSample(parentEl, null, nextReactionTime)
                        : lineageFactory.createInternal(parentEl, nextReactionTime);

                for (ReactElement childEl : children.get(i)) {
                    List<Lineage> elementLineages = lineages.get(childEl);
                    parent.addChild(elementLineages.remove(Randomizer.nextInt(elementLineages.size())));
                }

                lineages.get(parentEl).add(parent);
            }
    }
}
