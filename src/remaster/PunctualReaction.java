package remaster;

import beast.core.Function;
import beast.core.Input;
import beast.util.Randomizer;

/**
 * Class of reactions which occur at pre-determined times.
 */
public class PunctualReaction extends AbstractReaction {

    public Input<Function> pInput = new Input<>("p",
            "Probability of reaction firing per compatible " +
                    "configuration at specified times.");

    public Input<Function> nInput = new Input<>("n",
            "Number of reactions to fire at specified times.",
            Input.Validate.XOR, pInput);

    public Input<Function> timesInput = new Input<>("times",
            "Times at which punctual reactions occur.",
            Input.Validate.REQUIRED);

    double[] ps, ns, times;

    @Override
    public void initAndValidate() {
        if (pInput.get() != null)
            ps = pInput.get().getDoubleValues();

        if (nInput.get() != null)
            ns = nInput.get().getDoubleValues();

        times = timesInput.get().getDoubleValues();

        if ((ps != null && ps.length != times.length)
                || (ns != null && ns.length != times.length))
            throw new IllegalArgumentException("Number of times must match " +
                    "length of p or n inputs.");

        super.initAndValidate();
    }


    @Override
    public double getIntervalEndTime() {
        if (currentInterval < times.length)
            return times[currentInterval];

        return Double.POSITIVE_INFINITY;
    }

    public double implementEvent(TrajectoryState state) {
        if (ps != null)
            return implementPEvent(state);
        else
            return implementNEvent(state);
    }

    private double implementPEvent(TrajectoryState state) {
        double p = ps[currentInterval];
        double n;
        if (p == 0.0) {
            n = 0.0;
        } else {
            double N = Double.POSITIVE_INFINITY;
            for (ReactElement el : reactants.elementSet())
                N = Math.min(Math.floor(state.get(el)/reactants.count(el)), N);

            if (p == 1.0) {
                n = N;
            } else {
                // Sample number of reactions:
                double logP = N*Math.log(1-p);
                double C = Math.exp(logP);
                double logf = Math.log(p/(1-p));

                n = 0;
                double u = Randomizer.nextDouble();
                while (u > C) {
                    n += 1;
                    logP += logf + Math.log(N-n+1) - Math.log(n);
                    C += Math.exp(logP);
                }
            }
        }

        incrementState(state, n);
        return n;
    }

    private double implementNEvent(TrajectoryState state) {
        incrementState(state, ns[currentInterval]);

        return ns[currentInterval];
    }
}
