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

import static remaster.Util.nextBinomial;

/**
 * Class of reactions which occur at pre-determined times.
 */
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

        if (pInput.get() != null) {
            ps = new double[times.length];
            int pDim = pInput.get().getDimension();
            for (int i=0; i<times.length; i++)
                ps[i] = pInput.get().getArrayValue(i%pDim);
        }

        if (nInput.get() != null) {
            ns = new double[times.length];
            int nDim = nInput.get().getDimension();
            for (int i=0; i<times.length; i++)
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

    public void decrementInterval() {
        currentInterval -= 1;
    }

    public void resetIntervalToEnd() {
        currentInterval = times.length;
    }

    @Override
    public double[] getAllIntervalEndTimes() {
        return times;
    }

    public double getMaxReactCount(TrajectoryState state) {
        double N = Double.POSITIVE_INFINITY;
        for (ReactElement el : reactants.elementSet())
            N = Math.min(Math.floor(state.get(el)/reactants.count(el)), N);

        return N;
    }

    public double implementEvent(TrajectoryState state, boolean stochastic) {
        if (ps != null)
            return implementPEvent(state, stochastic);
        else
            return implementNEvent(state);
    }

    private double implementPEvent(TrajectoryState state, boolean stochastic) {
        double p = ps[currentInterval];
        double n;
        if (p == 0.0) {
            n = 0.0;
        } else {
            double N = getMaxReactCount(state);
            if (p == 1.0) {
                n = N;
            } else {
                if (stochastic) {
                    n =  nextBinomial(N, p);
                } else
                    n = p*N;
            }
        }

        incrementState(state, n);
        return n;
    }

    private double implementNEvent(TrajectoryState state) {
        incrementState(state, ns[currentInterval]);

        return ns[currentInterval];
    }
}
