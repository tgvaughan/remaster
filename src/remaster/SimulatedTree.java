package remaster;

import beast.core.Input;
import beast.evolution.tree.Node;
import beast.evolution.tree.Tree;

public class SimulatedTree extends Tree {

    public Input<StochasticTrajectory> trajectoryInput = new Input<>("trajectory",
            "Trajectory to use as basis of tree simulation.",
            Input.Validate.REQUIRED);

    StochasticTrajectory trajectory;

    @Override
    public void initAndValidate() {
        trajectory = trajectoryInput.get();

        super.initAndValidate();
    }

    public void doSimulation() {

        while (true) {

        }
    }
}
