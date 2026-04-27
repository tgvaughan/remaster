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

import beast.base.core.Description;
import beast.base.core.Input;
import beast.base.evolution.tree.Node;
import beast.base.spec.domain.*;
import beast.base.spec.inference.parameter.IntScalarParam;
import beast.base.spec.inference.parameter.RealScalarParam;
import beast.base.spec.inference.parameter.RealVectorParam;
import beast.base.spec.type.IntScalar;
import beast.base.spec.type.RealScalar;
import beast.base.util.Randomizer;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.ContinuousOutputModel;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.events.EventHandler;
import org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator;
import remaster.reactionboxes.BDReactionBox;
import remaster.reactionboxes.ContinuousBDReactionBox;
import remaster.reactionboxes.PunctualBDReactionBox;

import java.io.PrintStream;
import java.util.*;

@Description("An object representing a deterministic approximation to a" +
        "birth-death trajectory. Just as for StochasticTrajectory, " +
        "the birth-death model is specified via Functions representing" +
        "the various populations, and Reactions representing the reactions " +
        "producing the dynamics.  This object can be logged to produce a" +
        "TSV file which can be directly read into R for plotting.")
public class DeterministicTrajectory extends AbstractBDTrajectory {

    public Input<IntScalar<PositiveInt>> loggingGridSizeInput = new Input<>("loggingGridSize",
            "Number of grid points used to log trajectory.",
            new IntScalarParam<>(101, PositiveInt.INSTANCE));

    public Input<RealScalar<UnitInterval>> forwardRelativeStepSizeInput = new Input<>("forwardRelativeStepSize",
            "Integration time step length relative to to maxTime.",
            new RealScalarParam<>(1e-4, UnitInterval.INSTANCE));

    public Input<RealScalar<UnitInterval>> backwardRelativeStepSizeInput = new Input<>("backwardRelativeStepSize",
            "Integration time step length relative to to maxTime.",
            new RealScalarParam<>(1e-5, UnitInterval.INSTANCE));

    FirstOrderIntegrator integrator;

    public ContinuousOutputModel continuousOutputModel;
    public double stopTime;

    @Override
    public void initAndValidate() {
        super.initAndValidate();

        if (Double.isInfinite(maxTimeInput.get().get()))
            throw new IllegalArgumentException("Must specify finite maxTime for deterministic trajectories.");

        double maxFowardStep = forwardRelativeStepSizeInput.get().get()
                * maxTimeInput.get().get();
        integrator = new DormandPrince54Integrator(maxFowardStep*1e-3,
                maxFowardStep, 1e-3, 1e-4);

        doSimulation();
    }

    @Override
    public boolean doSimulation() {
        state.resetToInitial();

        FirstOrderDifferentialEquations system = new FirstOrderDifferentialEquations() {

            @Override
            public int getDimension() {
                return state.occupancies.length;
            }

            @Override
            public void computeDerivatives(double t, double[] y, double[] ydot)
                    throws MaxCountExceededException, DimensionMismatchException {
                Arrays.fill(ydot, 0.0);
                System.arraycopy(y, 0, state.occupancies, 0, y.length);

                for (ContinuousBDReactionBox reactionBox : continuousReactionBoxes) {
                    reactionBox.updatePropensity();

                    for (int i=0; i<state.occupancies.length; i++) {
                        ydot[i] += reactionBox.currentPropensity * reactionBox.stoichiometryVector[i];
                    }
                }
            }
        };


        EventHandler rateShiftHandler = new EventHandler() {

            List<BDReactionBox> reactionBoxesSortedByChangeTimes;
            Double[] changeTimes;

            @Override
            public void init(double t0, double[] y0, double tf) {
                Set<Double> changeTimeSet = new HashSet<>();
                for (BDReactionBox reactionBox : reactionBoxes) {
                    reactionBox.resetInterval();
                    for (double t : reactionBox.getAllIntervalEndTimes())
                        changeTimeSet.add(t);
                }

                changeTimes = changeTimeSet.toArray(new Double[0]);

                reactionBoxesSortedByChangeTimes = new ArrayList<>(reactionBoxes);
                reactionBoxesSortedByChangeTimes.sort(Comparator.comparingDouble(BDReactionBox::getIntervalEndTime));
            }

            @Override
            public double g(double t, double[] y) {
                double res = 1.0;
                for (Double changeTime : changeTimes)
                    res *= t - changeTime;

                return res;
            }

            @Override
            public Action eventOccurred(double t, double[] y, boolean increasing) {
                return Action.RESET_STATE;
            }

            @Override
            public void resetState(double t, double[] y) {
                double eventTime = reactionBoxesSortedByChangeTimes.getFirst().getIntervalEndTime();
                while (reactionBoxesSortedByChangeTimes.getFirst().getIntervalEndTime() == eventTime) {
                    BDReactionBox reactionBox = reactionBoxesSortedByChangeTimes.getFirst();

                    if (reactionBox instanceof PunctualBDReactionBox punctualReaction) {

                        System.arraycopy(y, 0, state.occupancies, 0, y.length);
                        punctualReaction.implementEvent(false);
                        System.arraycopy(state.occupancies, 0, y, 0, y.length);
                    }

                    reactionBox.incrementInterval();
                    reactionBoxesSortedByChangeTimes
                            .sort(Comparator.comparingDouble(BDReactionBox::getIntervalEndTime));
                }
            }
        };

        integrator.addEventHandler(rateShiftHandler,
                1e-2 * maxTimeInput.get().get(),
                1e-5 * maxTimeInput.get().get(),
                10);

        if (endCondition != null) {
            EventHandler endConditionHandler = new EventHandler() {
                double sign = 1.0;

                @Override
                public void init(double t0, double[] y0, double tf) {
                }

                @Override
                public double g(double t, double[] y) {
                    System.arraycopy(y, 0, state.occupancies, 0,
                            state.occupancies.length);

                    return endCondition.switchFunction() * sign;
                }

                @Override
                public Action eventOccurred(double t, double[] y, boolean increasing) {
                    System.out.println("Trajectory termination condition met: " + endsWhenInput.get());
                    sign = -sign;
                    return Action.STOP;
                }

                @Override
                public void resetState(double t, double[] y) {
                }
            };

            integrator.addEventHandler(endConditionHandler,
                    1e-2 * maxTimeInput.get().get(),
                    1e-5 * maxTimeInput.get().get(),
                    10);
        }

        continuousOutputModel = new ContinuousOutputModel();
        integrator.addStepHandler(continuousOutputModel);

        stopTime = integrator.integrate(system, 0, state.occupancies,
                maxTimeInput.get().get(), state.occupancies);

        if (acceptCondition != null && !acceptCondition.isMet()) {
            System.out.println("Trajectory acceptance condition not met: " + mustHaveInput.get());
            return false;
        }

        state.setFinal();
        return true;
    }

    @Override
    public Node simulateTree() throws SimulationFailureException {

        int Nt = (int)Math.round(1/backwardRelativeStepSizeInput.get().get());

        LineageFactory lineageFactory = new LineageFactory();
        Map<ReactElement, List<Lineage>> lineages = new HashMap<>();

        for (BDReactionBox reactionBox : reactionBoxes)
            reactionBox.resetIntervalToEnd();

        List<BDReactionBox> sortedReactionBoxes =
                new ArrayList<>(reactionBoxes);
        sortedReactionBoxes.sort(Comparator.comparingDouble(
                BDReactionBox::getIntervalStartTime).reversed());

        while (!sortedReactionBoxes.isEmpty()
                && sortedReactionBoxes.getFirst().getIntervalStartTime()>stopTime) {
            sortedReactionBoxes.getFirst().decrementInterval();
            sortedReactionBoxes.sort(Comparator.comparingDouble(
                    BDReactionBox::getIntervalStartTime).reversed());
        }

        double t = stopTime;
        double dt = stopTime/Nt;

        double u = Randomizer.nextDouble();

        while (t > 0.0) {
            continuousOutputModel.setInterpolatedTime(t);
            System.arraycopy(continuousOutputModel.getInterpolatedState(), 0,
                    state.occupancies, 0, state.occupancies.length);

            while (!sortedReactionBoxes.isEmpty() &&
                    sortedReactionBoxes.getFirst().getIntervalStartTime()>t) {
                BDReactionBox reactionBox = sortedReactionBoxes.getFirst();
                reactionBox.decrementInterval();
                if (reactionBox instanceof PunctualBDReactionBox punctualReactionBox) {
                    double n = punctualReactionBox.implementEvent(true);
                    for (int i = 0; i < n; i++) {
                        punctualReactionBox.incrementLineages(lineages, t,
                                lineageFactory, false);
                        punctualReactionBox.incrementState(state, -1);
                    }
                }
                sortedReactionBoxes.sort(Comparator.comparingDouble(
                        BDReactionBox::getIntervalStartTime).reversed());
            }

            for (ContinuousBDReactionBox reactionBox : continuousReactionBoxes) {
                reactionBox.updatePropensity();
                double totalInclusionProb =
                        reactionBox.getLineageInclusionProbability(lineages);

                double prob = reactionBox.currentPropensity*totalInclusionProb*dt;

                if (prob>1) {
                    throw new SimulationFailureException("Step reaction probability " +
                            "exceeds 1.  Consider reducing backwardRelativeStepSize " +
                            "input to DeterministicTrajectory.");
                }

                if (u < prob) {
                    reactionBox.incrementLineages(lineages, t,
                            lineageFactory, true);
                    u = Randomizer.nextDouble();
                    break;
                } else
                    u -= prob;
            }

            t -= dt;
        }

        List<Lineage> rootLineages = new ArrayList<>();
        for (ReactElement pop : lineages.keySet())
            rootLineages.addAll(lineages.get(pop));

        if (rootLineages.isEmpty()) {
            throw new SimulationFailureException("No lineages remaining.");
        }

        if (rootLineages.size()>1) {
            throw new SimulationFailureException("Multiple lineages remaining.");
        }

        lineageFactory.numberInternals(rootLineages.getFirst());
        lineageFactory.computeAgesFromTimes(rootLineages.getFirst());

        return rootLineages.getFirst();
    }

    @Override
    public void log(long sample, PrintStream out) {
        state.resetToInitial();

        state.addToLog(out, sample, 0, true);

        double T = maxTimeInput.get().get();
        double dt = T/loggingGridSizeInput.get().get();
        for (double t=dt; t<stopTime; t += dt) {
            continuousOutputModel.setInterpolatedTime(t);
            System.arraycopy(continuousOutputModel.getInterpolatedState(), 0,
                    state.occupancies, 0, state.occupancies.length);

            state.addToLog(out, sample, t, false);
        }

        out.print("\t");
    }

    /**
     * Testing
     */
    public static void main() {

        RealVectorParam<NonNegativeReal> popX = new RealVectorParam<>(new double[] {1.0}, NonNegativeReal.INSTANCE);
        popX.setID("X");

        Reaction birth = new Reaction();
        birth.initByName(
                "value", "X -> 2X",
                "rate", new RealVectorParam<>(new double[]{2.0}, NonNegativeReal.INSTANCE));

        Reaction death = new Reaction();
        death.initByName(
                "value", "X -> 0",
                "rate", new RealVectorParam<>(new double[]{1.0}, NonNegativeReal.INSTANCE));

        DeterministicTrajectory traj = new DeterministicTrajectory();
        traj.initByName("population", popX,
                "reaction", birth,
                "reaction", death,
        "maxTime", new RealScalarParam<>(4.0, PositiveReal.INSTANCE));
    }

}
