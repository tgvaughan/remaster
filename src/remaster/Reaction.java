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

/**
 * Class of continuous-time reactions.
 */
public class Reaction extends AbstractReaction {

    public Input<Function> rateInput = new Input<>("rate",
            "Per-configuration rate constant.");

    public Input<Function> changeTimesInput = new Input<>("changeTimes",
            "Rate change times.");

    double[] rates, changeTimes;

    @Override
    public void initAndValidate() {

        if (rateInput.get() == null)
            throw new IllegalArgumentException("No rate provided.");

        rates = rateInput.get().getDoubleValues();
        changeTimes = changeTimesInput.get() == null
                ? new double[0] : changeTimesInput.get().getDoubleValues();

        if (changeTimes.length != rates.length-1)
            throw new IllegalArgumentException("Number of change times must " +
                    "equal number of distinct rates - 1.");

        super.initAndValidate();
    }

    @Override
    public double getIntervalEndTime() {
        if (currentInterval < changeTimes.length)
            return changeTimes[currentInterval];
        else
            return Double.POSITIVE_INFINITY;
    }

    public double getIntervalStartTime() {
        if (currentInterval > 0)
            return changeTimes[currentInterval-1];

        return Double.NEGATIVE_INFINITY;
    }

    @Override
    public double[] getAllIntervalEndTimes() {
        return changeTimes;
    }

    public double getIntervalRate() {
        return rates[currentInterval];
    }

    @Override
    public void resetIntervalToEnd() {
        currentInterval = changeTimes.length;
    }

    // Main method for debugging
    public static void main(String[] args) {

        Reaction reaction = new Reaction();
        reaction.initByName("rate", 0.01,
                "value", "S + I -> 2I");
    }

}
