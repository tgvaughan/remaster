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

import beast.base.core.Function;
import beast.base.inference.parameter.RealParameter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BDConditionTest {

    @Test
    public void testSimple() {

        List<Function> allPops = new ArrayList<>();
        Set<String> samplePopNames = new HashSet<>();

        RealParameter X = new RealParameter("1 20 30");
        X.setID("X");
        allPops.add(X);

        RealParameter Y = new RealParameter("12");
        Y.setID("Y");
        allPops.add(Y);

        RealParameter S = new RealParameter("100");
        S.setID("S");
        allPops.add(S);

        BDTrajectoryState state = new BDTrajectoryState(allPops, samplePopNames);

        BDCondition condition;
        condition = new BDCondition("X[0] < Y", state);
        assertTrue(condition.isMet());

        condition = new BDCondition("X[1] < Y", state);
        assertFalse(condition.isMet());

        condition = new BDCondition("X[2] > Y", state);
        assertTrue(condition.isMet());

        condition = new BDCondition("S == 100", state);
        assertTrue(condition.isMet());
    }

    @Test
    public void testSwitch() {

        List<Function> allPops = new ArrayList<>();
        Set<String> samplePopNames = new HashSet<>();

        RealParameter X = new RealParameter("1");
        X.setID("X");
        allPops.add(X);

        RealParameter Y = new RealParameter("0");
        Y.setID("Y");
        allPops.add(Y);

        BDTrajectoryState state = new BDTrajectoryState(allPops, samplePopNames);

        BDCondition condition;
        condition = new BDCondition("X < Y", state);

        for (double xval = 0.0; xval < 20.0; xval += 0.1) {
            state.occupancies[state.popIndices.get("X")] = xval;

            if (xval > 15.0)
                state.occupancies[state.popIndices.get("Y")] = 18.0;
            else
                state.occupancies[state.popIndices.get("Y")] = 12.0;

            double res = condition.switchFunction();
//            System.out.println(xval + " " + res);

            if (xval < 12.0 || (xval > 15.0 && xval < 18.0))
                assertTrue(res > 0);
            else
                assertTrue(res < 0);
        }
    }
}