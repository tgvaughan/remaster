package remaster.model;

import beast.core.CalculationNode;
import beast.core.Input;

public abstract class Reaction extends CalculationNode {

    /**
     * Proposes a maximum time step size the reaction will accept.
     *
     * @param systemState current state
     * @return time step
     */
    public abstract double proposeStepSize(SystemState systemState);

    /**
     *
     * @param systemState
     * @param dt
     * @return
     */
    public abstract double incrementState(SystemState systemState, double dt);
}
