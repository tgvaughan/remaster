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

import beast.base.core.BEASTObject;
import beast.base.core.Function;

import java.util.*;

/**
 * Class of objects representing the state of birth death trajectories
 * (both exact and deterministic approximations)
 */
public class BDTrajectoryState {

    public double[] occupancies, initialOccupancies, finalOccupancies;

    public Map<String, Integer> popIndices = new HashMap<>();
    public Map<String, Integer> popDims = new HashMap<>();

    public Set<String> samplePopNames;

    public Set<AbstractReaction> sampleProducingReactions;

    public BDTrajectoryState(List<Function> allPops, Set<String> samplePopNames)  {

        int nextIdx = 0;

        for (Function popFunc : allPops) {
            String popName = ((BEASTObject)popFunc).getID().intern();
            popIndices.put(popName, nextIdx);
            popDims.put(popName, popFunc.getDimension());
            nextIdx += popFunc.getDimension();
        }

        occupancies = new double[nextIdx];
        initialOccupancies = new double[nextIdx];
        finalOccupancies = new double[nextIdx];

        for (Function popFunc : allPops) {
            String popName = ((BEASTObject)popFunc).getID().intern();
            System.arraycopy(popFunc.getDoubleValues(), 0, initialOccupancies,
                    popIndices.get(popName), popFunc.getDimension());
        }

        System.arraycopy(initialOccupancies, 0, occupancies, 0,
                initialOccupancies.length);

        this.samplePopNames = samplePopNames;
        sampleProducingReactions = new HashSet<>();
    }

    public boolean hasPopNamed(String string) {
        return popIndices.containsKey(string);
    }

    public double get(String popName, int idx) {
        return occupancies[popIndices.get(popName) + idx];
    }

    public double get(ReactElement el) {
        return occupancies[popIndices.get(el.name) + el.idx];
    }

    public int getOffset(ReactElement el) {
        return popIndices.get(el.name) + el.idx;
    }

    public Double[] getArray(String popName) {
        Double[] res = new Double[popDims.get(popName)];

        int offset = popIndices.get(popName);
        for (int i=0; i<res.length; i++)
            res[i] = occupancies[offset+i];

        return res;
    }

    public int getTotalSubpopCount() {
        return occupancies.length;
    }

    public void incrementOccupancy(String popName, int idx, double amount) {
        occupancies[popIndices.get(popName)+idx] += amount;
    }

    public boolean isValid() {
        for (Double val : occupancies) {
            if (val < 0.0)
                return false;
        }

        return true;
    }


    public void setFinal() {
        System.arraycopy(occupancies, 0, finalOccupancies, 0,
                occupancies.length);
    }

    public void resetToInitial() {
        System.arraycopy(initialOccupancies, 0, occupancies, 0,
                occupancies.length);
    }

    public void resetToFinal() {
        System.arraycopy(finalOccupancies, 0, occupancies, 0,
                occupancies.length);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (String popName : popIndices.keySet()) {
            sb.append(":").append(popName).append("=");
            int idx = popIndices.get(popName);
            for (int i=idx; i<idx+popDims.get(popName); i++) {
                if (i>idx)
                    sb.append(",");
                sb.append(occupancies[i]);
            }
        }

        return sb.toString();
    }

}
