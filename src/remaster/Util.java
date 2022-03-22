package remaster;

import beast.math.GammaFunction;

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
}
