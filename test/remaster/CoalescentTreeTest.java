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

import beast.base.evolution.tree.coalescent.ConstantPopulation;
import beast.base.util.DiscreteStatistics;
import beast.base.util.Randomizer;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

public class CoalescentTreeTest {

    @Test
    public void pairwiseCoalTest() {
        Randomizer.setSeed(53);

        ConstantPopulation pop = new ConstantPopulation();
        pop.initByName("popSize", "10.0");
        pop.setID("pop");

        PunctualReaction makeLeaves = new PunctualReaction();
        makeLeaves.initByName("n", "2", "times", "0", "value", "0 -> pop");

        CoalescentTrajectory traj = new CoalescentTrajectory();
        traj.initByName("population", pop,
                "reaction", makeLeaves);

        SimulatedTree tree = new SimulatedTree();
        tree.initByName("trajectory", traj);

        double[] coalTimes = IntStream.range(0, 100000)
                .mapToDouble(i -> {
                    tree.doSimulation();
                    return tree.getRoot().getHeight();})
                .toArray();

        Assert.assertEquals(10.0, DiscreteStatistics.mean(coalTimes), 0.1);
    }
}
