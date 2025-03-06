/*
 * Copyright (c) 2023 ETH Zurich
 *
 * This file is part of remaster.
 *
 * remaster is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * remaster is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with remaster. If not, see <https://www.gnu.org/licenses/>.
 */

package remaster;

import beast.base.core.BEASTObject;
import beast.base.core.Input;
import beast.base.core.Log;
import beast.base.inference.Distribution;
import beast.base.inference.Logger;
import beast.base.inference.Runnable;

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

        Log.info.println("Starting ReMASTER simulation.");

        Set<BEASTObject> alreadySeen = new HashSet<>();
        for (int i=0; i<nSimsInput.get(); i++) {

            alreadySeen.clear();

            // Reinitialize objects (kicks off simulation)
            for (BEASTObject beastObject : beastObjectInput.get())
                reinitializeObjects(beastObject, alreadySeen, i==0);

            // Log state
            for (Logger logger : loggersInput.get()) {
                reinitializeObjects(logger, alreadySeen, false);
                logger.log(i);
            }
        }

        // Finalize loggers
        for (Logger logger : loggersInput.get())
            logger.close();

        Log.info.println("ReMASTER simulation complete.");

    }

    /**
     * Run initAndValidate() on all descendant inputs in post-order.
     *
     * @param rootObject root of object graph
     * @param alreadyInitialized set of objects which have already been seen
     */
    private void reinitializeObjects(BEASTObject rootObject, Set<BEASTObject> alreadyInitialized,
                                     Boolean markOnly) {

        if (alreadyInitialized.contains(rootObject))
            return;

        alreadyInitialized.add(rootObject);

        for (Input<?> input : rootObject.getInputs().values()) {
            if (input.get() != null) {
                if (input.get() instanceof BEASTObject) {
                    reinitializeObjects((BEASTObject)input.get(), alreadyInitialized, markOnly);
                } else if (input.get() instanceof List) {
                    for (Object el : (List<?>) input.get()) {
                        if (el instanceof BEASTObject)
                            reinitializeObjects((BEASTObject) el, alreadyInitialized, markOnly);
                    }
                }
            }
        }

        if (!markOnly)
            rootObject.initAndValidate();

        if (rootObject instanceof Distribution)
            ((Distribution)rootObject).calculateLogP();
    }

}
