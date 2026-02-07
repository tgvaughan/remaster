/*
 * Copyright (c) 2023-2026 ETH Zürich
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
import org.junit.Assert;
import org.junit.Test;
import remaster.DeterministicTrajectory;
import remaster.Reaction;

public class DeterministicTrajectoryTest {

    @Test
    public void linearBDtest() {

        RealParameter X = new RealParameter("1");
        X.setID("X");

        Reaction birth = new Reaction();
        birth.initByName("rate", "1.5", "value", "X -> 2X");

        Reaction death = new Reaction();
        death.initByName("rate", "1.0", "value", "X -> 0");

        DeterministicTrajectory traj = new DeterministicTrajectory();
        traj.initByName("population", X,
                "reaction", birth,
                "reaction", death,
                "maxTime", "5");

        traj.continuousOutputModel.setInterpolatedTime(5);
        Assert.assertEquals(12.18249, traj.continuousOutputModel.getInterpolatedState()[0], 1e-5);
    }
}
