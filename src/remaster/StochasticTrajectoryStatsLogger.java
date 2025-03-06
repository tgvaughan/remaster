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

import beast.base.core.BEASTObject;
import beast.base.core.Input;
import beast.base.core.Loggable;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class StochasticTrajectoryStatsLogger extends BEASTObject implements Loggable {

    public Input<StochasticTrajectory> stochasticTrajectoryInput = new Input<>(
            "traj", "Stochastic trajectory.", Input.Validate.REQUIRED);

    StochasticTrajectory traj;

    List<String> popNames;

    @Override
    public void initAndValidate() {
        traj = stochasticTrajectoryInput.get();
        popNames = new ArrayList<>(traj.state.popDims.keySet());
    }

    @Override
    public void init(PrintStream out) {
        String prefix = traj.getID() != null
                ? traj.getID() + "."
                : "traj.";

        out.print(prefix + "eventCount\t");

        for (String pop : popNames) {
            for (int i=0; i<traj.state.popDims.get(pop); i++)
                out.print(prefix + pop + i + "_finalSize\t");
        }
    }

    @Override
    public void log(long sample, PrintStream out) {


        out.print(traj.events.size() + "\t");

        traj.state.resetToFinal();
        for (String pop : popNames) {
            for (int i=0; i<traj.state.popDims.get(pop); i++)
                out.print(traj.state.get(pop, i) + "\t");
        }
    }

    @Override
    public void close(PrintStream out) {

    }
}
