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

package remaster;

import beast.base.core.Description;
import beast.base.core.Function;
import beast.base.core.Input;
import beast.base.spec.domain.NonNegativeInt;
import beast.base.spec.domain.NonNegativeReal;
import beast.base.spec.domain.UnitInterval;
import beast.base.spec.type.IntVector;
import beast.base.spec.type.RealVector;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Description("Reactions which occur at pre-determined times.")
public class PunctualReaction extends AbstractReaction {

    public Input<RealVector<UnitInterval>> pInput = new Input<>("p",
            "Probability of reaction firing per compatible " +
                    "configuration at specified times.");

    public Input<IntVector<NonNegativeInt>> nInput = new Input<>("n",
            "Number of reactions to fire at specified times.",
            Input.Validate.XOR, pInput);

    public Input<RealVector<NonNegativeReal>> timesInput = new Input<>("times",
            "Times at which punctual reactions occur.",
            Input.Validate.REQUIRED);

    double[] ps, times;
    int[] ns;

    @Override
    public void initAndValidate() {
        times = new double[timesInput.get().size()];
        for (int i=0; i<times.length; i++)
            times[i] = timesInput.get().get(i);

        // Sort times, keeping association with ns/ps:
        Integer[] indices = new Integer[times.length];
        for (int i=0; i<indices.length; i++)
            indices[i] = i;
        Arrays.sort(indices, (i1, i2) -> Double.compare(times[i1], times[i2]));
        Arrays.sort(times);

        // Fill ps and ns (using sorted time indices):

        if (pInput.get() != null) {
            ps = new double[times.length];
            int pDim = pInput.get().size();
            for (int i : indices)
                ps[i] = pInput.get().get(i%pDim);
        }

        if (nInput.get() != null) {
            ns = new int[times.length];
            int nDim = nInput.get().size();
            for (int i : indices)
                ns[i] = nInput.get().get(i%nDim);
        }

        super.initAndValidate();
    }

    @Override
    public double getIntervalEndTime() {
        if (currentInterval < times.length)
            return times[currentInterval];

        return Double.POSITIVE_INFINITY;
    }

    public double getIntervalStartTime() {
        if (currentInterval > 0)
            return times[currentInterval-1];

        return Double.NEGATIVE_INFINITY;
    }

    @Override
    public void resetIntervalToEnd() {
        currentInterval = times.length;
    }

    @Override
    public double[] getAllIntervalEndTimes() {
        return times;
    }

    public boolean isPReaction() {
        return ps != null;
    }

    public double getNextP() {
        return ps[currentInterval];
    }

    public double getNextN() {
        return ns[currentInterval];
    }
}
