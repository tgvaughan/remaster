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

import beast.base.core.Description;
import beast.base.core.Function;
import beast.base.core.Input;

import java.util.Arrays;
import java.util.Comparator;

@Description("Reactions which occur at pre-determined times.")
public class PunctualReaction extends AbstractReaction {

    public Input<Function> pInput = new Input<>("p",
            "Probability of reaction firing per compatible " +
                    "configuration at specified times.");

    public Input<Function> nInput = new Input<>("n",
            "Number of reactions to fire at specified times.",
            Input.Validate.XOR, pInput);

    public Input<Function> timesInput = new Input<>("times",
            "Times at which punctual reactions occur.",
            Input.Validate.REQUIRED);

    double[] ps, ns, times;

    @Override
    public void initAndValidate() {
        times = timesInput.get().getDoubleValues();

        // Sort times, keeping association with ns/ps:
        Integer[] indices = new Integer[times.length];
        for (int i=0; i<indices.length; i++)
            indices[i] = i;
        Arrays.sort(indices, (i1, i2) -> Double.compare(times[i1], times[i2]));
        Arrays.sort(times);

        // Fill ps and ns (using sorted time indices):

        if (pInput.get() != null) {
            ps = new double[times.length];
            int pDim = pInput.get().getDimension();
            for (int i : indices)
                ps[i] = pInput.get().getArrayValue(i%pDim);
        }

        if (nInput.get() != null) {
            ns = new double[times.length];
            int nDim = nInput.get().getDimension();
            for (int i : indices)
                ns[i] = nInput.get().getArrayValue(i%nDim);
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
