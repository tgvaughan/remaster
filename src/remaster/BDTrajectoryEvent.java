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

import remaster.reactionboxes.BDReactionBox;

/**
 * Events produced by birth-death trajectories.
 */
public class BDTrajectoryEvent {
    public double time;
    public double multiplicity;
    public BDReactionBox reactionBox;

    public BDTrajectoryEvent(double time, BDReactionBox reactionBox, double multiplicity) {
        this.time = time;
        this.reactionBox = reactionBox;
        this.multiplicity = multiplicity;
    }

    @Override
    public String toString() {
        return reactionBox.toString() + " at t=" + time
                + " (multiplicity " + multiplicity + ")";
    }
}
