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

/**
 * Events produced by stochastic trajectories.
 */
public class TrajectoryEvent {
    public double time;
    public double multiplicity;
    public AbstractReaction reaction;

    public TrajectoryEvent(double time, AbstractReaction reaction, double multiplicity) {
        this.time = time;
        this.reaction = reaction;
        this.multiplicity = multiplicity;
    }

    @Override
    public String toString() {
        return reaction.toString() + " at t=" + time
                + " (multiplicity " + multiplicity + ")";
    }
}
