package remaster;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeterministicTrajectory extends AbstractTrajectory {

    @Override
    public void initAndValidate() {
        super.initAndValidate();

        doSimulation();
    }

    @Override
    public boolean doSimulation() {
        return false;
    }


    @Override
    public void log(long sample, PrintStream out) {
        // TODO
    }

    public class ODESystem implements FirstOrderDifferentialEquations {

        TrajectoryState state;

        List<Reaction> reactions;

        Map<Reaction, double[]> vs;

        int dimension;

        public ODESystem(List<Reaction> reactions, TrajectoryState state) {
            this.state = state;
            this.reactions = reactions;

            dimension = state.getTotalSubpopCount();

            vs = new HashMap<>();
            for (Reaction reaction : reactions) {
                double[] v = new double[dimension];
                for (ReactElement reactElement : reaction.reactants)
                    v[state.getOffset(reactElement)] += 1;

                for (ReactElement reactElement : reaction.products) {
                    v[state.getOffset(reactElement)] -= 1;
                }
                vs.put(reaction, v);
            }
        }

        @Override
        public int getDimension() {
            return dimension;
        }

        @Override
        public void computeDerivatives(double t, double[] y, double[] ydot) throws MaxCountExceededException, DimensionMismatchException {

            Arrays.fill(ydot, 0.0);

            for (Reaction reaction : reactions) {
                reaction.updatePropensity(state);
                double[] v = vs.get(reaction);

                for (int i=0; i<dimension; i++) {
                    ydot[i] += reaction.currentPropensity * v[i];
                }
            }
        }
    }

}
