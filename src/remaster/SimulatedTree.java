package remaster;

import beast.core.Input;
import beast.evolution.tree.Node;
import beast.evolution.tree.Tree;

import java.sql.Array;
import java.util.*;

public class SimulatedTree extends Tree {

    public Input<StochasticTrajectory> trajectoryInput = new Input<>("trajectory",
            "Trajectory to use as basis of tree simulation.",
            Input.Validate.REQUIRED);

    StochasticTrajectory trajectory;

    @Override
    public void initAndValidate() {
        trajectory = trajectoryInput.get();

        super.initAndValidate();

        doSimulation();
    }

    public void doSimulation() {

        List<StochasticTrajectory.TrajectoryEvent> eventList =
                new ArrayList<>(trajectory.events);
        Collections.reverse(eventList);

        Map<ReactElement, List<Lineage>> lineages = new HashMap<>();

        trajectory.state.resetToFinal();

        LineageFactory lineageFactory = new LineageFactory();

        for (StochasticTrajectory.TrajectoryEvent event : eventList) {
            event.reaction.incrementLineages(lineages, trajectory.state, event.time, lineageFactory);
            event.reaction.reverseIncremementState(trajectory.state);
        }

        List<Lineage> rootLineages = new ArrayList<>();
        for (ReactElement pop : lineages.keySet())
            rootLineages.addAll(lineages.get(pop));

        // Restrict reactions to ensure this is impossible
        if (rootLineages.isEmpty())
            throw new IllegalStateException("No lineages remaining.");

        // Might allow this in future
        if (rootLineages.size()>1)
            throw new IllegalStateException("Multiple lineages remaining.");

        lineageFactory.numberInternals(rootLineages.get(0));

        assignFromWithoutID(new Tree(rootLineages.get(0)));
    }
}
