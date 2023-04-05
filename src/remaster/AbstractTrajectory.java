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

import beast.base.core.Input;
import beast.base.core.Loggable;
import beast.base.evolution.tree.Node;
import beast.base.inference.CalculationNode;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTrajectory extends CalculationNode implements Loggable {

    public Input<List<AbstractReaction>> reactionsInput = new Input<>("reaction",
            "Reaction", new ArrayList<>());

    List<AbstractReaction> reactions;
    List<Reaction> continuousReactions;
    List<PunctualReaction> punctualReactions;

    @Override
    public void initAndValidate() {
        reactions = reactionsInput.get();
        continuousReactions = new ArrayList<>();
        punctualReactions = new ArrayList<>();

        for (AbstractReaction reaction : reactions) {
            if (reaction instanceof Reaction)
                continuousReactions.add((Reaction) reaction);
            else if (reaction instanceof PunctualReaction)
                punctualReactions.add((PunctualReaction) reaction);
            else
                throw new IllegalArgumentException("Unsupported reaction type: " +
                        reaction.getClass().getCanonicalName());
        }
    }

    public abstract Node simulateTree() throws SimulationFailureException;

    public static class SimulationFailureException extends Exception {
        public SimulationFailureException(String message) {
            super(message);
        }
    }

    @Override
    public void init(PrintStream out) {
        if (getID() != null)
            out.print(getID() + "\t");
        else
            out.print("trajectory\t");
    }

    @Override
    public void close(PrintStream out) {

    }
}
