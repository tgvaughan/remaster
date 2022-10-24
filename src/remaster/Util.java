package remaster;

import beast.base.util.GammaFunction;
import beast.base.util.Randomizer;

public class Util {

    /**
     * Log binomial coefficient allowing floating point arguments.
     *
     * @param n number of elements to choose from
     * @param k number of elements to choose
     * @return log of the corresponding binomial coefficient
     */
    public static double logChoose(double n, double k) {
        return GammaFunction.lnGamma(n + 1.0) - GammaFunction.lnGamma(k + 1.0)
                - GammaFunction.lnGamma(n - k + 1.0);
    }

    /**
     * Draw sample from a binomial distribution.
     *
     * @param N number of trials
     * @param p success probability of each trial
     * @return number of successes
     */
    public static double nextBinomial(double N, double p) {
        double logP = N * Math.log(1 - p);
        double C = Math.exp(logP);
        double logf = Math.log(p / (1 - p));

        int n = 0;
        double u = Randomizer.nextDouble();
        while (u > C) {
            n += 1;
            logP += logf + Math.log(N - n + 1) - Math.log(n);
            C += Math.exp(logP);
        }

        return n;
    }
}
