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
import beast.base.core.Function;
import beast.base.core.Input;
import beast.base.inference.parameter.RealParameter;
import beast.base.spec.domain.NonNegativeReal;
import beast.base.spec.domain.PositiveReal;
import beast.base.spec.inference.parameter.RealScalarParam;
import beast.base.spec.type.RealScalar;
import beast.base.spec.type.RealVector;
import remaster.reactionboxes.BDReactionBox;
import remaster.reactionboxes.ContinuousBDReactionBox;
import remaster.reactionboxes.PunctualBDReactionBox;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractBDTrajectory extends AbstractTrajectory {

    public Input<List<RealVector<NonNegativeReal>>> populationsInput = new Input<>("population",
            "Population or compartment", new ArrayList<>());

    public Input<List<RealVector<NonNegativeReal>>> samplePopulationsInput = new Input<>("samplePopulation",
            "Sample population or compartment", new ArrayList<>());

    public Input<String> endsWhenInput = new Input<>("endsWhen",
            "End conditions.");

    public Input<String> mustHaveInput = new Input<>("mustHave",
            "Acceptance predicate.");

    public Input<RealScalar<PositiveReal>> maxTimeInput = new Input<>("maxTime",
            "Maximum length of simulation", new RealScalarParam<>(Double.POSITIVE_INFINITY, PositiveReal.INSTANCE));

    BDCondition endCondition, acceptCondition;

    BDTrajectoryState state;
    List<ContinuousBDReactionBox> continuousReactionBoxes;
    List<PunctualBDReactionBox> punctualReactionBoxes;
    List<BDReactionBox> reactionBoxes;

    @Override
    public void initAndValidate() {
        super.initAndValidate();

        Set<String> samplePopNames = new HashSet<>();
        for (RealVector<NonNegativeReal> popFunc : samplePopulationsInput.get()) {
            String popName = ((BEASTObject) popFunc).getID().intern();
            samplePopNames.add(popName);
        }
        List<RealVector<NonNegativeReal>> allPops = new ArrayList<>(populationsInput.get());
        allPops.addAll(samplePopulationsInput.get());

        state = new BDTrajectoryState(allPops, samplePopNames);

        if (endsWhenInput.get() != null)
            endCondition = new BDCondition(endsWhenInput.get(), state);

        if (mustHaveInput.get() != null)
            acceptCondition = new BDCondition(mustHaveInput.get(), state);

        continuousReactionBoxes = new ArrayList<>();
        for (Reaction reaction : continuousReactions)
            continuousReactionBoxes.add(new ContinuousBDReactionBox(reaction, samplePopNames, state));

        punctualReactionBoxes = new ArrayList<>();
        for (PunctualReaction reaction : punctualReactions)
            punctualReactionBoxes.add(new PunctualBDReactionBox(reaction, samplePopNames, state));

        reactionBoxes = new ArrayList<>();
        reactionBoxes.addAll(continuousReactionBoxes);
        reactionBoxes.addAll(punctualReactionBoxes);
    }


    public abstract boolean doSimulation();


}
