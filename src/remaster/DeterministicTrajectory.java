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

import beast.base.core.Description;
import beast.base.core.Function;
import beast.base.core.Input;
import beast.base.evolution.tree.Node;
import beast.base.inference.parameter.IntegerParameter;
import beast.base.inference.parameter.RealParameter;
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

    public Input<Function> loggingGridSizeInput = new Input<>("loggingGridSize",
            "Number of grid points used to log trajectory.",
            new IntegerParameter("101"));

    public Input<Function> forwardRelativeStepSizeInput = new Input<>("forwardRelativeStepSize",
            "Integration time step length relative to to maxTime.",
            new RealParameter("1e-4"));

    public Input<Function> backwardRelativeStepSizeInput = new Input<>("backwardRelativeStepSize",
            "Integration time step length relative to to maxTime.",
            new RealParameter("1e-5"));

    FirstOrderIntegrator integrator;

    public ContinuousOutputModel continuousOutputModel;
    public double stopTime;

    @Override
    public void initAndValidate() {
        super.initAndValidate();

        if (Double.isInfinite(maxTimeInput.get().getArrayValue()))
            throw new IllegalArgumentException("Must specify finite maxTime for deterministic trajectories.");

        double maxFowardStep = forwardRelativeStepSizeInput.get().getArrayValue()
                * maxTimeInput.get().getArrayValue();
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
                double eventTime = reactionBoxesSortedByChangeTimes.get(0).getIntervalEndTime();
                while (reactionBoxesSortedByChangeTimes.get(0).getIntervalEndTime() == eventTime) {
                    BDReactionBox reactionBox = reactionBoxesSortedByChangeTimes.get(0);

                    if (reactionBox instanceof PunctualBDReactionBox) {
                        PunctualBDReactionBox punctualReaction = (PunctualBDReactionBox) reactionBox;

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
                1e-2 * maxTimeInput.get().getArrayValue(),
                1e-5 * maxTimeInput.get().getArrayValue(),
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
                    1e-2 * maxTimeInput.get().getArrayValue(),
                    1e-5 * maxTimeInput.get().getArrayValue(),
                    10);
        }

        continuousOutputModel = new ContinuousOutputModel();
        integrator.addStepHandler(continuousOutputModel);

        stopTime = integrator.integrate(system, 0, state.occupancies,
                maxTimeInput.get().getArrayValue(), state.occupancies);

        if (acceptCondition != null && !acceptCondition.isMet()) {
            System.out.println("Trajectory acceptance condition not met: " + mustHaveInput.get());
            return false;
        }

        state.setFinal();
        return true;
    }

    @Override
    public Node simulateTree() throws SimulationFailureException {

        int Nt = (int)Math.round(1/backwardRelativeStepSizeInput.get().getArrayValue());

        LineageFactory lineageFactory = new LineageFactory();
        Map<ReactElement, List<Lineage>> lineages = new HashMap<>();

        for (BDReactionBox reactionBox : reactionBoxes)
            reactionBox.resetIntervalToEnd();

        List<BDReactionBox> sortedReactionBoxes =
                new ArrayList<>(reactionBoxes);
        sortedReactionBoxes.sort(Comparator.comparingDouble(
                BDReactionBox::getIntervalStartTime).reversed());

        while (!sortedReactionBoxes.isEmpty()
                && sortedReactionBoxes.get(0).getIntervalStartTime()>stopTime) {
            sortedReactionBoxes.get(0).decrementInterval();
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
                    sortedReactionBoxes.get(0).getIntervalStartTime()>t) {
                BDReactionBox reactionBox = sortedReactionBoxes.get(0);
                reactionBox.decrementInterval();
                if (reactionBox instanceof PunctualBDReactionBox) {
                    PunctualBDReactionBox punctualReactionBox = (PunctualBDReactionBox)reactionBox;
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

        lineageFactory.numberInternals(rootLineages.get(0));
        lineageFactory.computeAgesFromTimes(rootLineages.get(0));

        return rootLineages.get(0);
    }

    @Override
    public void log(long sample, PrintStream out) {
        state.resetToInitial();

        state.addToLog(out, sample, 0, true);

        double T = maxTimeInput.get().getArrayValue();
        double dt = T/loggingGridSizeInput.get().getArrayValue();
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
     * @param args
     */
    public static void main(String[] args) {

        RealParameter popX = new RealParameter("1.0");
        popX.setID("X");

        Reaction birth = new Reaction();
        birth.initByName(
                "value", "X -> 2X",
                "rate", new RealParameter("2.0"));

        Reaction death = new Reaction();
        death.initByName(
                "value", "X -> 0",
                "rate", new RealParameter("1.0"));

        DeterministicTrajectory traj = new DeterministicTrajectory();
        traj.initByName("population", popX,
                "reaction", birth,
                "reaction", death,
                "maxTime", new RealParameter("5.0"));
    }

}
