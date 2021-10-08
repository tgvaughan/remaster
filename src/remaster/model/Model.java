package remaster.model;

import beast.core.BEASTObject;
import beast.core.Input;

import java.util.ArrayList;
import java.util.List;

public class Model extends BEASTObject {

    public Input<SystemState> systemStateInput = new Input<>("systemState", "System state", Input.Validate.REQUIRED);
    public Input<List<Reaction>> reactionsInput = new Input<>("reaction", "Reaction", new ArrayList<>());

    public Input<Double> simulationTimeInput = new Input<>("simulationTime", "Simulation time", Input.Validate.REQUIRED);

    SystemState systemState;
    List<Reaction> reactions;
    double simulationTime;

    @Override
    public void initAndValidate() {
        systemState = systemStateInput.get();
        reactions = reactionsInput.get();
        simulationTime = simulationTimeInput.get();
    }

    public Trajectory simulate(boolean forceFSE) {

        systemState.initAndValidate();

        double t = 0.0;

        while (true) {

            double dt = simulationTime;

            for (Reaction reaction : reactions)
                dt = Math.min(dt, reaction.proposeStepSize(systemState));

            for (Reaction reaction : reactions)
                reaction.incrementState(systemState, dt);

            t += dt;
        }
    }
}
