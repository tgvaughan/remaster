/*
 * Copyright (c) 2023 Tim Vaughan
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
import beast.base.core.Function;
import beast.base.core.Input;
import beast.base.core.Log;
import beast.base.inference.parameter.RealParameter;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractBDTrajectory extends AbstractTrajectory {

    public Input<List<Function>> populationsInput = new Input<>("population",
            "Population or compartment", new ArrayList<>());

    public Input<List<Function>> samplePopulationsInput = new Input<>("samplePopulation",
            "Sample population or compartment", new ArrayList<>());

    public Input<String> endsWhenInput = new Input<>("endsWhen",
            "End conditions.");

    public Input<String> mustHaveInput = new Input<>("mustHave",
            "Acceptance predicate.");

    public Input<Function> maxTimeInput = new Input<>("maxTime",
            "Maximum length of simulation", new RealParameter("Infinity"));

    Condition endCondition, acceptCondition;

    BDTrajectoryState state;

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

        state = new BDTrajectoryState(allPops, samplePopNames);

        if (endsWhenInput.get() != null)
            endCondition = new Condition(endsWhenInput.get(), state);

        if (mustHaveInput.get() != null)
            acceptCondition = new Condition(mustHaveInput.get(), state);


        for (AbstractReaction reaction : reactions) {
            if (!state.processAndValidateReaction(reaction))
                throw new IllegalStateException("Invalid reaction detected.");

        }
    }


    public abstract boolean doSimulation();


}
