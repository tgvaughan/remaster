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

import beast.base.spec.domain.NonNegativeReal;
import beast.base.spec.inference.parameter.RealVectorParam;
import beast.base.spec.type.RealVector;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BDConditionTest {

    @Test
    public void testSimple() {

        List<RealVector<NonNegativeReal>> allPops = new ArrayList<>();
        Set<String> samplePopNames = new HashSet<>();

        RealVectorParam<NonNegativeReal> X = new RealVectorParam<>(new double[]{1, 20, 30}, NonNegativeReal.INSTANCE);
        X.setID("X");
        allPops.add(X);

        RealVectorParam<NonNegativeReal> Y = new RealVectorParam<>(new double[]{12}, NonNegativeReal.INSTANCE);
        Y.setID("Y");
        allPops.add(Y);

        RealVectorParam<NonNegativeReal> S = new RealVectorParam<>(new double[]{100}, NonNegativeReal.INSTANCE);
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

        List<RealVector<NonNegativeReal>> allPops = new ArrayList<>();
        Set<String> samplePopNames = new HashSet<>();

        RealVectorParam<NonNegativeReal> X = new RealVectorParam<>(new double[]{1}, NonNegativeReal.INSTANCE);
        X.setID("X");
        allPops.add(X);

        RealVectorParam<NonNegativeReal> Y = new RealVectorParam<>(new double[]{0}, NonNegativeReal.INSTANCE);
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