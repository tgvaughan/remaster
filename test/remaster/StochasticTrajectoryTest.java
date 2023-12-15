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

import beast.base.inference.parameter.RealParameter;
import beast.base.util.DiscreteStatistics;
import beast.base.util.Randomizer;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

public class StochasticTrajectoryTest {

    BDTrajectoryState getStateAtTime(StochasticTrajectory traj, double t) {
        traj.state.resetToInitial();

        for (BDTrajectoryEvent event : traj.events) {
            if (event.time>t)
                break;
            event.reactionBox.incrementState(traj.state, event.multiplicity);
        }

        return traj.state;
    }

    @Test
    public void linearBDtest() {
        Randomizer.setSeed(53);

        RealParameter X = new RealParameter("1");
        X.setID("X");

        Reaction birth = new Reaction();
        birth.initByName("rate", "1.5", "value", "X -> 2X");

        Reaction death = new Reaction();
        death.initByName("rate", "1.0", "value", "X -> 0");

        double[] finalPopSizes = IntStream.range(0, 10000)
                .mapToObj(i -> {
                    StochasticTrajectory traj = new StochasticTrajectory();
                    traj.initByName("population", X,
                            "reaction", birth,
                            "reaction", death,
                            "maxTime", "5");
                    return traj;})
                .map(traj -> getStateAtTime(traj, 5))
                .mapToDouble(state -> state.get("X", 0))
                .toArray();

        Assert.assertEquals(12.18249, DiscreteStatistics.mean(finalPopSizes), 0.1);
    }
}
