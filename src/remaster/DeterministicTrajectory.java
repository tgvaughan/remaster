package remaster;

import beast.core.BEASTObject;
import beast.core.Function;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;

import java.io.PrintStream;
import java.util.*;

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

        Map<String, Integer> popIndices;

        List<Reaction> reactions;

        double[] y, dydt;

        public ODESystem(List<Function> pops, List<Function> sampPops,
                         List<Reaction> reactions) {

            this.reactions = reactions;

            popIndices = new HashMap<>();

            List<Function> allPops = new ArrayList<>(pops);
            allPops.addAll(sampPops);

            int idx = 0;
            for (Function pop : allPops) {
                String popName = ((BEASTObject)pop).getID().intern();
                popIndices.put(popName, idx);
                idx += pop.getDimension();
            }

            y = new double[idx];
            dydt = new double[idx];

            for (Function pop : allPops) {
                String popName = ((BEASTObject)pop).getID().intern();
                idx = popIndices.get(popName);
                for (int i=idx; i<pop.getDimension(); i++)
                    System.arraycopy(pop.getDoubleValues(), 0, y, idx, pop.getDimension());
            }
        }

        @Override
        public int getDimension() {
            return y.length;
        }

        @Override
        public void computeDerivatives(double t, double[] y, double[] ydot) throws MaxCountExceededException, DimensionMismatchException {

            Arrays.fill(ydot, 0.0);

            for (Reaction reaction : reactions) {

            }

        }
    }

}
