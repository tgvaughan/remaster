package remaster;


import beast.base.core.BEASTObject;
import beast.base.core.Function;
import beast.base.core.Input;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractBDTrajectory extends AbstractTrajectory {

    public Input<List<Function>> samplePopulationsInput = new Input<>("samplePopulation",
            "Sample population or compartment", new ArrayList<>());

    public Input<String> endsWhenInput = new Input<>("endsWhen",
            "End conditions.");

    public Input<String> mustHaveInput = new Input<>("mustHave",
            "Acceptance predicate.");

    Condition endCondition, acceptCondition;

    TrajectoryState state;

    @Override
    public void initAndValidate() {
        super.initAndValidate();

        Set<String> samplePopNames = new HashSet<>();
        for (Function popFunc : samplePopulationsInput.get()) {
            String popName = ((BEASTObject) popFunc).getID().intern();
            samplePopNames.add(popName);
        }
        List<Function> allPops = new ArrayList<>(populationsInput.get());
        allPops.addAll(samplePopulationsInput.get());

        state = new TrajectoryState(allPops);

        if (endsWhenInput.get() != null)
            endCondition = new Condition(endsWhenInput.get(), state);

        if (mustHaveInput.get() != null)
            acceptCondition = new Condition(mustHaveInput.get(), state);

        for (AbstractReaction reaction : reactions) {
            reaction.computeStoichiometry(state);
            reaction.markSamples(samplePopNames);
            if (!reaction.isValid(state, samplePopNames))
                throw new IllegalStateException("Invalid reaction detected.");

        }
    }

    public abstract boolean doSimulation();

}
