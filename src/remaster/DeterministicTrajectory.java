package remaster;

import beast.core.parameter.RealParameter;
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

    @Override
    public void initAndValidate() {
        super.initAndValidate();

        if (Double.isInfinite(maxTimeInput.get().getArrayValue()))
            throw new IllegalArgumentException("Must specify finite maxTime for deterministic trajectories.");

        doSimulation();
    }

    Map<Reaction, double[]> vs;

    public void computeStoichiometry(TrajectoryState state, List<Reaction> reactions) {
        vs = new HashMap<>();
        for (Reaction reaction : reactions) {
            double[] v = new double[state.getTotalSubpopCount()];
            for (ReactElement reactElement : reaction.reactants)
                v[state.getOffset(reactElement)] -= 1;

            for (ReactElement reactElement : reaction.products) {
                v[state.getOffset(reactElement)] += 1;
            }
            vs.put(reaction, v);
        }
    }

    public ContinuousOutputModel continuousOutputModel;

    @Override
    public boolean doSimulation() {
        state.resetToInitial();

        computeStoichiometry(state, continuousReactions);

        FirstOrderDifferentialEquations system = new FirstOrderDifferentialEquations() {

            @Override
            public int getDimension() {
                return state.occupancies.length;
            }

            @Override
            public void computeDerivatives(double t, double[] y, double[] ydot)
                    throws MaxCountExceededException, DimensionMismatchException {
                Arrays.fill(ydot, 0.0);

                for (Reaction reaction : continuousReactions) {

                    reaction.updatePropensity(state);
                    double[] v = vs.get(reaction);

                    for (int i=0; i<state.occupancies.length; i++) {
                        ydot[i] += reaction.currentPropensity * v[i];
                    }
                }
            }
        };

        DormandPrince54Integrator integrator = new DormandPrince54Integrator(
                1e-6, 1, 1e-5, 1e-10);

        continuousOutputModel = new ContinuousOutputModel();
        integrator.addStepHandler(continuousOutputModel);

        integrator.integrate(system, 0, state.occupancies,
                maxTimeInput.get().getArrayValue(), state.occupancies);

        continuousOutputModel.setInterpolatedTime(5);
        double[] res = continuousOutputModel.getInterpolatedState();

        return false;
    }


    @Override
    public void log(long sample, PrintStream out) {
        // TODO
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
