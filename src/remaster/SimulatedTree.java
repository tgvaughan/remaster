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
import beast.base.core.Log;
import beast.base.evolution.tree.Node;
import beast.base.evolution.tree.Tree;

public class SimulatedTree extends Tree {

    public Input<AbstractTrajectory> trajectoryInput = new Input<>("trajectory",
            "Trajectory to use as basis of tree simulation.",
            Input.Validate.REQUIRED);

    public Input<Integer> maxRetriesInput = new Input<>("maxRetries",
            "Maximum number of times to retry tree simulation after failure " +
                    "to simulate tree with at least one tip and exactly one " +
                    "root lineage.", 10);

    public Input<Boolean> removeSingletonsInput = new Input<>(
            "removeSingletonNodes",
            "Remove nodes with exactly one parent and one child. " +
                    "(Default false.)",
            false);

    AbstractTrajectory trajectory;
    int maxRetries;

    @Override
    public void initAndValidate() {
        trajectory = trajectoryInput.get();
        maxRetries = maxRetriesInput.get();

        super.initAndValidate();

        doSimulation();
    }

    public void doSimulation() {

        Node root = null;

        int retries = maxRetries;
        while (root == null && retries >= 0) {
            try {
                root = trajectory.simulateTree();
            } catch (AbstractBDTrajectory.SimulationFailureException e) {
                Log.err.print("Tree simulation error: " + e.getMessage());
                retries -= 1;

                if (retries >= 0) {
                    Log.err.println(" Retrying.");
                    trajectory.initAndValidate();
                } else
                    Log.err.println();
            }
        }

        if (root == null) {
            Log.err.println("Failed to simulate tree with at least one leaf " +
                    "and exactly one root lineage. (maxRetries = " +
                    maxRetries + ")");
            System.exit(1);
        }

        if (removeSingletonsInput.get())
            root = Util.getSingletonFreeTree(root);

        assignFromWithoutID(new Tree(root));

        // Recover taxon set
        if (m_taxonset.get() != null) {
            if (getLeafNodeCount() != m_taxonset.get().getTaxonCount())
                throw new IllegalArgumentException("Number of leaves in simulated tree does not match the number of provided taxa.");

            for (int i=0; i<getLeafNodeCount(); i++) {
                getNode(i).setID(m_taxonset.get().getTaxonId(i));
            }

        }

    }
}
