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

import beast.base.core.Function;
import beast.base.inference.parameter.RealParameter;
import org.junit.Test;
import remaster.BDTrajectoryState;
import remaster.Reaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ContinuousBDReactionBoxTest {

    @Test
    public void test() {

        List<Function> allPops = new ArrayList<>();
        RealParameter X = new RealParameter("25");
        X.setID("X");
        allPops.add(X);

        RealParameter Y = new RealParameter("3");
        Y.setID("Y");
        allPops.add(Y);

        RealParameter S = new RealParameter("0");
        S.setID("S");
        allPops.add(S);

        Set<String> samplePopNames = new HashSet<>();
        samplePopNames.add("S");

        BDTrajectoryState state = new BDTrajectoryState(allPops, samplePopNames);

        Reaction birth = new Reaction();
        birth.initByName("value", "X -> 2X",
                "rate", "2.0");

        ContinuousBDReactionBox birthBox = new ContinuousBDReactionBox(birth, samplePopNames, state);
        birthBox.updatePropensity();

        assertEquals(50.0, birthBox.currentPropensity, 1e-14);

        Reaction death = new Reaction();
        death.initByName("value", "2X -> X",
                "rate", "0.1");

        ContinuousBDReactionBox deathBox = new ContinuousBDReactionBox(death, samplePopNames, state);
        deathBox.updatePropensity();

        assertEquals(25*24*0.5*0.1, deathBox.currentPropensity, 1e-14);

    }
}
