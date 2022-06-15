package remaster;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;

import java.io.PrintStream;

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

        @Override
        public int getDimension() {
            return 0;
        }

        @Override
        public void computeDerivatives(double v, double[] doubles, double[] doubles1) throws MaxCountExceededException, DimensionMismatchException {

        }
    }

}
