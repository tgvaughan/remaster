package remaster;

import beast.core.Function;
import beast.core.Input;
import beast.core.parameter.IntegerParameter;
import beast.core.parameter.RealParameter;
import beast.evolution.tree.Node;
import beast.util.Randomizer;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.ContinuousOutputModel;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeterministicTrajectory extends AbstractTrajectory {

    public Input<Function> loggingGridSizeInput = new Input<>("loggingGridSize",
            "Number of grid points used to log trajectory.",
            new IntegerParameter("101"));

    @Override
    public void initAndValidate() {
        super.initAndValidate();

        if (Double.isInfinite(maxTimeInput.get().getArrayValue()))
            throw new IllegalArgumentException("Must specify finite maxTime for deterministic trajectories.");

        doSimulation();
    }



    public ContinuousOutputModel continuousOutputModel;

    @Override
    public boolean doSimulation() {
        state.resetToInitial();

        FirstOrderDifferentialEquations system = new FirstOrderDifferentialEquations() {

            @Override
            public int getDimension() {
                return state.occupancies.length;
            }

            @Override
            public void computeDerivatives(double t, double[] y, double[] ydot)
                    throws MaxCountExceededException, DimensionMismatchException {
                Arrays.fill(ydot, 0.0);
                System.arraycopy(y, 0, state.occupancies, 0, y.length);

                for (Reaction reaction : continuousReactions) {

                    reaction.updatePropensity(state);
                    double[] v = reaction.stoichiometryVector;

                    for (int i=0; i<state.occupancies.length; i++) {
                        ydot[i] += reaction.currentPropensity * v[i];
                    }
                }
            }
        };

        DormandPrince54Integrator integrator = new DormandPrince54Integrator(
                 1e-5*maxTimeInput.get().getArrayValue(),
                 1e-2*maxTimeInput.get().getArrayValue(),
                1, 0.01);

        continuousOutputModel = new ContinuousOutputModel();
        integrator.addStepHandler(continuousOutputModel);

        integrator.integrate(system, 0, state.occupancies,
                maxTimeInput.get().getArrayValue(), state.occupancies);

        return false;
    }

    @Override
    public Node simulateTree() {

        return null;
    }

    @Override
    public void log(long sample, PrintStream out) {
        state.resetToInitial();

        out.print("t=0");
        out.print(state);

        double T = maxTimeInput.get().getArrayValue();
        double dt = T/loggingGridSizeInput.get().getArrayValue();
        for (double t=dt; t<maxTimeInput.get().getArrayValue(); t += dt) {

            out.print(";");
            out.print("t=" + t);

            continuousOutputModel.setInterpolatedTime(t);
            System.arraycopy(continuousOutputModel.getInterpolatedState(), 0,
                    state.occupancies, 0, state.occupancies.length);

            out.print(state);
        }

        out.print("\t");
    }

    /**
     * Testing
     * @param args
     */
    public static void main(String[] args) {

        RealParameter popX = new RealParameter("1.0");
        popX.setID("X");

        Reaction birth = new Reaction();
        birth.initByName(
                "value", "X -> 2X",
                "rate", new RealParameter("2.0"));

        Reaction death = new Reaction();
        death.initByName(
                "value", "X -> 0",
                "rate", new RealParameter("1.0"));

        DeterministicTrajectory traj = new DeterministicTrajectory();
        traj.initByName("population", popX,
                "reaction", birth,
                "reaction", death,
                "maxTime", new RealParameter("5.0"));
    }

}
