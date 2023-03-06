package remaster;

import beast.base.core.Function;
import beast.base.core.Input;
import beast.base.core.Loggable;
import beast.base.evolution.tree.Node;
import beast.base.inference.CalculationNode;
import beast.base.inference.parameter.RealParameter;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTrajectory extends CalculationNode implements Loggable {

    public Input<List<Function>> populationsInput = new Input<>("population",
            "Population or compartment", new ArrayList<>());

    public Input<List<AbstractReaction>> reactionsInput = new Input<>("reaction",
            "Reaction", new ArrayList<>());

    public Input<Function> maxTimeInput = new Input<>("maxTime",
            "Maximum length of simulation", new RealParameter("Infinity"));

    List<AbstractReaction> reactions;
    List<Reaction> continuousReactions;
    List<PunctualReaction> punctualReactions;

    @Override
    public void initAndValidate() {
        reactions = reactionsInput.get();
        continuousReactions = new ArrayList<>();
        punctualReactions = new ArrayList<>();

        for (AbstractReaction reaction : reactions) {
            if (reaction instanceof Reaction)
                continuousReactions.add((Reaction) reaction);
            else if (reaction instanceof PunctualReaction)
                punctualReactions.add((PunctualReaction) reaction);
            else
                throw new IllegalArgumentException("Unsupported reaction type: " +
                        reaction.getClass().getCanonicalName());
        }
    }

    public abstract Node simulateTree() throws SimulationFailureException;

    public static class SimulationFailureException extends Exception {
        public SimulationFailureException(String message) {
            super(message);
        }
    }

    @Override
    public void init(PrintStream out) {
        if (getID() != null)
            out.print(getID() + "\t");
    }

    @Override
    public void close(PrintStream out) {

    }
}
