package remaster;

import beast.core.BEASTObject;
import beast.core.Function;
import beast.core.Input;
import beast.core.Loggable;
import beast.util.Randomizer;

import java.io.PrintStream;
import java.util.*;

public class StochasticTrajectory extends BEASTObject implements Loggable {

    public Input<List<Function>> populationsInput = new Input<>("population",
            "Population or compartment", new ArrayList<>());

    public Input<List<Function>> samplePopulationsInput = new Input<>("samplePopulation",
            "Sample population or compartment", new ArrayList<>());

    public Input<List<Reaction>> reactionsInput = new Input<>("reaction",
            "Reaction", new ArrayList<>());

    Map<String, Double[]> state, initialState;
    Set<String> samplePops;
    List<Reaction> reactions;

    List<TrajectoryEvent> events;

    @Override
    public void initAndValidate() {
        state = new HashMap<>();
        initialState = new HashMap<>();
        events = new ArrayList<>();

        for (Function popFunc : populationsInput.get())
            initPop(popFunc);

        samplePops = new HashSet<>();
        for (Function popFunc : samplePopulationsInput.get())
            samplePops.add(initPop(popFunc));

        reactions = reactionsInput.get();

        doSimulation();
    }

    private String initPop(Function popFunc) {
        String popName = ((BEASTObject)popFunc).getID();

        Double[] popArray = new Double[popFunc.getDimension()];
        for (int i=0; i<popArray.length; i++)
            popArray[i] = popFunc.getArrayValue(i);
        initialState.put(popName, popArray);
        state.put(popName, new Double[popArray.length]);

        return popName;
    }

    public void resetState() {
        for (String popName : state.keySet())
            System.arraycopy(initialState.get(popName),
                    0, state.get(popName),
                    0, state.get(popName).length);

    }

    public void doSimulation() {
        resetState();
        events.clear();

        double t=0.0;

        while (true) {
            double a0 = 0.0;
            for (Reaction reaction : reactions) {
                reaction.updatePropensity(state);
                a0 += reaction.currentPropensity;
            }

            if (a0 == 0.0)
                break;

            double delta = Randomizer.nextExponential(a0);
            t += delta;

            double u = Randomizer.nextDouble()*a0;

            Reaction thisReaction = null;
            for (Reaction reaction : reactions) {
                if (u < reaction.currentPropensity) {
                    thisReaction = reaction;
                    break;
                } else
                    u -= reaction.currentPropensity;
            }

            if (thisReaction == null)
                throw new IllegalStateException("Reaction selection loop fell through.");

            events.add(new TrajectoryEvent(t, thisReaction));
            thisReaction.incrementState(state);
        }


    }

    @Override
    public void init(PrintStream out) {
        if (getID() != null)
            out.print(getID() + "\t");
    }

    private String getStateString(Map<String,Double[]> state) {
        StringBuilder sb = new StringBuilder();

        for (String popName : state.keySet()) {
            sb.append(":").append(popName).append("=");
            Double[] values  = state.get(popName);
            for (int i=0; i<values.length; i++) {
                if (i>0)
                    sb.append(",");
                sb.append(values[i]);
            }
        }

        return sb.toString();
    }

    @Override
    public void log(long sample, PrintStream out) {
        resetState();

        boolean isFirst = true;
        for (TrajectoryEvent event : events) {
            if (isFirst) {
                isFirst = false;
                out.print("t=0");
            } else {
                out.print(",");
                out.print("t=" + event.time);
            }

            out.print(getStateString(state));
            event.reaction.incrementState(state);
        }

        out.print("\t");
    }

    @Override
    public void close(PrintStream out) {

    }
}
