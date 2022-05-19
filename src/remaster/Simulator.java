package remaster;

import beast.core.*;
import beast.core.Runnable;

import java.util.*;

public class Simulator extends Runnable {

    public Input<List<BEASTObject>> beastObjectInput = new Input<>(
            "simulate",
            "BEASTObject constituting simulated quantity.",
            new ArrayList<>());

    public Input<Integer> nSimsInput = new Input<>(
            "nSims",
            "Number of independent simulations to run (default 1)",
            1);

    public Input<List<Logger>> loggersInput = new Input<>(
            "logger",
            "Logger used to write results to screen or disk.",
            new ArrayList<>());

    @Override
    public void initAndValidate() { }

    @Override
    public void run() throws Exception {

        if (beastObjectInput.get().isEmpty())
            throw new IllegalArgumentException("Need to specify at least one" +
                    " simulation object.");

        // Initialize loggers
        for (Logger logger : loggersInput.get()) {
            logger.init();
        }

        Set<BEASTObject> alreadySeen = new HashSet<>();
        for (int i=0; i<nSimsInput.get(); i++) {

            // Reinitialize objects (kicks off simulation)
            if (i>0) {
                alreadySeen.clear();
                for (BEASTObject beastObject : beastObjectInput.get())
                    reinitializeObjects(beastObject, alreadySeen);
            }

            // Log state
            for (Logger logger : loggersInput.get())
                logger.log(i);
        }

        // Finalize loggers
        for (Logger logger : loggersInput.get())
            logger.close();
    }

    /**
     * Run initAndValidate() on all descendant inputs in post-order.
     *
     * @param rootObject root of object graph
     * @param alreadyInitialized set of objects which have already been seen
     */
    private void reinitializeObjects(BEASTObject rootObject, Set<BEASTObject> alreadyInitialized) {

        if (alreadyInitialized.contains(rootObject))
            return;

        for (Input<?> input : rootObject.getInputs().values()) {
            if (input.get() != null) {
                if (input.get() instanceof BEASTObject) {
                    reinitializeObjects((BEASTObject)input.get(), alreadyInitialized);
                } else if (input.get() instanceof List) {
                    for (Object el : (List<?>) input.get()) {
                        if (el instanceof BEASTObject)
                            reinitializeObjects((BEASTObject) el, alreadyInitialized);
                    }
                }
            }
        }

        rootObject.initAndValidate();
    }

}
