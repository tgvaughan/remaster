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

import beast.base.core.Function;
import beast.base.core.Input;
import beast.base.core.Loggable;
import beast.base.evolution.tree.Node;
import beast.base.evolution.tree.coalescent.PopulationFunction;
import beast.base.inference.CalculationNode;
import beast.base.util.Randomizer;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoalescentTrajectory extends AbstractTrajectory {

    public Input<List<PopulationFunction>> popFuncInput = new Input<>("populationModel",
        "Population represented by a beast PopulationFunction object",
        new ArrayList<>());

    @Override
    public void initAndValidate() {
    }

    @Override
    public Node simulateTree() throws SimulationFailureException {

        LineageFactory lineageFactory = new LineageFactory();
        Map<ReactElement, List<Lineage>> lineages = new HashMap<>();

        double tEnd = maxTimeInput.get() != null
                ? maxTimeInput.get().getArrayValue()
                : Double.POSITIVE_INFINITY;

        List<CoalescentReaction> coalReactions = new ArrayList<>();
        for (PopulationFunction popFunc : popFuncInput.get())
            coalReactions.add(new CoalescentReaction(popFunc));
        for (Reaction reaction : continuousReactions)
            coalReactions.add(new CoalescentReaction(reaction));



        double t = 0.0;
        while (t < tEnd) {

            // Sample time increment

            // Passed any boundaries?

            // Select and implement reaction

        }

        return null;
    }

    static class CoalescentReaction {
        PopulationFunction popFunc;
        Reaction reaction;

        public CoalescentReaction(PopulationFunction popFunc) {
            this.popFunc = popFunc;
        }

        public CoalescentReaction(Reaction reaction) {
            this.reaction = reaction;
        }

        public double getNextReactionTime(double currentTime) {
            double u = Randomizer.nextDouble();

            if (popFunc != null) {
                return PopulationFunction.Utils.getSimulatedInterval(popFunc, k, currentTime);
            } else {
                double prop = reaction.updatePropensity(state);
                double dt = -Math.log(u)/prop;
                double t = currentTime;
                while (t+dt > reaction.getIntervalEndTime()) {
                    u -= Math.exp(-(t - reaction.getIntervalEndTime()) * prop);
                    t = reaction.getIntervalEndTime();
                    reaction.incrementInterval();
                    prop = reaction.updatePropensity(state);
                    dt = -Math.log(u)/prop;
                }

                return t + dt;
            }
        }

    }

    @Override
    public void log(long sample, PrintStream out) {
        // TODO
    }

}
