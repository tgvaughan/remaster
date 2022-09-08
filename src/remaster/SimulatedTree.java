package remaster;

import beast.core.Input;
import beast.evolution.tree.Tree;

public class SimulatedTree extends Tree {

    public Input<AbstractTrajectory> trajectoryInput = new Input<>("trajectory",
            "Trajectory to use as basis of tree simulation.",
            Input.Validate.REQUIRED);

    AbstractTrajectory trajectory;

    @Override
    public void initAndValidate() {
        trajectory = trajectoryInput.get();

        super.initAndValidate();

        doSimulation();
    }

    public void doSimulation() {
        assignFromWithoutID(new Tree(trajectory.simulateTree()));
    }
}
