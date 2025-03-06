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

package remaster.reactionboxes;

import remaster.BDTrajectoryState;
import remaster.ReactElement;
import remaster.Reaction;

import java.util.Set;

import static remaster.Util.logChoose;

public class ContinuousBDReactionBox extends BDReactionBox {
    Reaction reaction;
    public double currentPropensity;

    public ContinuousBDReactionBox(Reaction reaction, Set<String> samplePopNames, BDTrajectoryState state) {
        super(reaction, samplePopNames, state);

        this.reaction = reaction;
    }

    /**
     * Update current propensity corresponding to the given reaction.
     *
     * @return calculated propensity
     */
    public double updatePropensity() {
        currentPropensity = reaction.getIntervalRate();
        for (ReactElement reactElement : reaction.reactants.elementSet()) {
            currentPropensity *= Math.exp(logChoose(state.get(reactElement),
                    reaction.reactants.count(reactElement)));
        }

        return currentPropensity;
    }
}
