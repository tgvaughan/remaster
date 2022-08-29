package remaster;

import beast.core.CalculationNode;
import beast.core.Function;
import beast.core.Input;
import beast.core.Loggable;
import beast.core.parameter.RealParameter;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTrajectory extends CalculationNode implements Loggable {

    public Input<List<Function>> populationsInput = new Input<>("population",
            "Population or compartment", new ArrayList<>());

    public Input<List<Function>> samplePopulationsInput = new Input<>("samplePopulation",
            "Sample population or compartment", new ArrayList<>());

    public Input<List<AbstractReaction>> reactionsInput = new Input<>("reaction",
            "Reaction", new ArrayList<>());

    public Input<Function> maxTimeInput = new Input<>("maxTime",
            "Maximum length of simulation", new RealParameter("Infinity"));

    public Input<String> endsWhenInput = new Input<>("endsWhen",
            "End conditions.");

    public Input<String> mustHaveInput = new Input<>("mustHave",
            "Acceptance predicate.");

    List<AbstractReaction> reactions;
    List<Reaction> continuousReactions;
    List<PunctualReaction> punctualReactions;
    Condition endCondition, acceptCondition;

    TrajectoryState state;

    @Override
    public void initAndValidate() {
        state = new TrajectoryState(populationsInput.get(), samplePopulationsInput.get());
        reactions = reactionsInput.get();
        continuousReactions = new ArrayList<>();
        punctualReactions = new ArrayList<>();

        if (endsWhenInput.get() != null)
            endCondition = new Condition(endsWhenInput.get(), state);

        if (mustHaveInput.get() != null)
            acceptCondition = new Condition(mustHaveInput.get(), state);

        for (AbstractReaction reaction : reactions) {
            reaction.markSamples(state);
            if (!reaction.isValid(state))
                throw new IllegalStateException("Invalid reaction detected.");

            if (reaction instanceof Reaction)
                continuousReactions.add((Reaction)reaction);
            else if (reaction instanceof PunctualReaction)
                punctualReactions.add((PunctualReaction)reaction);
            else
                throw new IllegalArgumentException("Unsupported reaction type: " +
                        reaction.getClass().getCanonicalName());
        }
    }

    public abstract boolean doSimulation();

    @Override
    public void init(PrintStream out) {
        if (getID() != null)
            out.print(getID() + "\t");
    }

    @Override
    public void close(PrintStream out) { }

}
