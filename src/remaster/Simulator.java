package remaster;

import beast.core.BEASTObject;
import beast.core.Input;
import beast.core.Logger;
import beast.core.Runnable;

import java.util.ArrayList;
import java.util.List;

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

        for (int i=0; i<nSimsInput.get(); i++) {
            if (i>0) {
                for (BEASTObject beastObject : beastObjectInput.get())
                    beastObject.initAndValidate();
            }

            // Log state
            for (Logger logger : loggersInput.get())
                logger.log(i);
        }

        // Finalize loggers
        for (Logger logger : loggersInput.get())
            logger.close();
    }
}
