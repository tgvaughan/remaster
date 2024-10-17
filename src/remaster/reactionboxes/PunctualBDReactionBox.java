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

import remaster.BDTrajectoryState;
import remaster.PunctualReaction;
import remaster.ReactElement;

import java.util.Set;

import static remaster.Util.nextBinomial;

public class PunctualBDReactionBox extends BDReactionBox {
    PunctualReaction reaction;

    public PunctualBDReactionBox(PunctualReaction reaction, Set<String> samplePopNames, BDTrajectoryState state) {
        super(reaction, samplePopNames, state);

        this.reaction = reaction;
    }

    public double getMaxReactCount() {
        double N = Double.POSITIVE_INFINITY;
        for (ReactElement el : reaction.reactants.elementSet())
            N = Math.min(Math.floor(state.get(el)/reaction.reactants.count(el)), N);

        return N;
    }

    public double implementEvent(boolean stochastic) {
        if (reaction.isPReaction())
            return implementPEvent(stochastic);
        else
            return implementNEvent();
    }

    private double implementPEvent(boolean stochastic) {
        double p = reaction.getNextP();
        double n;
        if (p == 0.0) {
            n = 0.0;
        } else {
            double N = getMaxReactCount();
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

    private double implementNEvent() {
        if (getMaxReactCount() < reaction.getNextN())
            return -1; // Insufficient reactants for reaction

        incrementState(state, reaction.getNextN());
        return reaction.getNextN();
    }

    public double getIntervalStartTime() {
        return reaction.getIntervalStartTime();
    }

    public void resetIntervalToEnd() {
        reaction.resetIntervalToEnd();
    }

    public void decrementInterval() {
        reaction.decrementInterval();
    }
}
