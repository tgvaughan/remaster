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
                    "to simulate tree with exactly one root lineage.", 10);

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

        int retries = 0;
        while (root == null && retries <= maxRetries) {
            try {
                root = trajectory.simulateTree();
            } catch (AbstractTrajectory.TreeSimulationFailureException e) {
                Log.err.print("Tree simulation error: " + e.getMessage());
                retries += 1;

                if (retries <= maxRetries)
                    Log.err.print(" Retrying.");
                Log.err.println();
            }
        }

        if (root == null) {
            Log.err.println("Failed to simulate tree with exactly one root " +
                    "lineage. (maxRetries = " + maxRetries + ")");
            System.exit(1);
        }

        assignFromWithoutID(new Tree(root));
    }
}
