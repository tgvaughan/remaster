package remaster;

import beast.base.core.Function;
import beast.base.core.Input;
import beast.base.util.Binomial;

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
            throw new IllegalArgumentException("Number of change times must " +
                    "equal number of distinct rates - 1.");

        super.initAndValidate();
    }

    @Override
    public double getIntervalEndTime() {
        if (currentInterval < changeTimes.length)
            return changeTimes[currentInterval];
        else
            return Double.POSITIVE_INFINITY;
    }

    @Override
    public double[] getAllIntervalEndTimes() {
        return changeTimes;
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
            currentPropensity *= Binomial.choose(state.get(reactElement),
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
