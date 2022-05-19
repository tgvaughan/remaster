package remaster;

import beast.core.Function;
import beast.core.Input;
import beast.math.Binomial;
import beast.util.Randomizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class of continuous-time reactions.
 */
public class Reaction extends AbstractReaction {

    public Input<Function> rateInput = new Input<>("rate",
            "Per-configuration rate constant.");

    public Input<Function> changeTimesInput = new Input<>("changeTimes",
            "Rate change times.");

    double[] rates, changeTimes;

    @Override
    public void initAndValidate() {

        if (rateInput.get() == null)
            throw new IllegalArgumentException("No rate provided.");

        rates = rateInput.get().getDoubleValues();
        changeTimes = changeTimesInput.get() == null
                ? new double[0] : changeTimesInput.get().getDoubleValues();

        if (changeTimes.length != rates.length-1)
            throw new IllegalArgumentException("Number of change times must equal number of rate shifts - 1.");

        super.initAndValidate();
    }

    @Override
    public double getIntervalEndTime() {
        if (currentInterval < changeTimes.length)
            return changeTimes[currentInterval];
        else
            return Double.POSITIVE_INFINITY;
    }

    /**
     * Current propensity.
     */
    public double currentPropensity;

    /**
     * Update current propensity using the provided state.
     *
     * @param state trajectory state
     * @return calculated propensity
     */
    public double updatePropensity(TrajectoryState state) {
        currentPropensity = rates[currentInterval];
        for (ReactElement reactElement : reactants.elementSet()) {
            currentPropensity *= Binomial.choose(state.get(reactElement.name)[reactElement.idx],
                    reactants.count(reactElement));
        }

        return currentPropensity;
    }



    // Main method for debugging
    public static void main(String[] args) {

        Reaction reaction = new Reaction();
        reaction.initByName("rate", 0.01,
                "value", "S + I -> 2I");
    }

}
