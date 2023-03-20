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
import org.apache.commons.math3.ode.nonstiff.ClassicalRungeKuttaIntegrator;

import java.io.PrintStream;
import java.util.*;

/**
 * Class of objects representing deterministic approximations to birth-death
 * trajectories.
 */
public class DeterministicTrajectory extends AbstractBDTrajectory {

    public Input<Function> loggingGridSizeInput = new Input<>("loggingGridSize",
            "Number of grid points used to log trajectory.",
            new IntegerParameter("101"));

    public Input<Function> relativeStepSizeInput = new Input<>("relativeStepSize",
            "Integration time step length relative to to maxTime.",
            new RealParameter("1e-4"));
    FirstOrderIntegrator integrator;

    public ContinuousOutputModel continuousOutputModel;
    public double stopTime;

    HashMap<AbstractReaction, double[]> stoichiometryVectors;

    @Override
    public void initAndValidate() {
        super.initAndValidate();

        if (Double.isInfinite(maxTimeInput.get().getArrayValue()))
            throw new IllegalArgumentException("Must specify finite maxTime for deterministic trajectories.");

        integrator = new ClassicalRungeKuttaIntegrator(
                relativeStepSizeInput.get().getArrayValue()
                        *maxTimeInput.get().getArrayValue());

        stoichiometryVectors = new HashMap<>();
        for (AbstractReaction reaction : reactions)
            stoichiometryVectors.put(reaction, computeStoichiometry(reaction, state));

        doSimulation();
    }

    /**
     * Compute stoichiometry vector.  Called on initialisation of
     * trajectory.
     *
     * @param state Trajectory state instance
     */
    public double[] computeStoichiometry(AbstractReaction reaction, BDTrajectoryState state) {
        double[] stoichiometryVector = new double[state.getTotalSubpopCount()];
        for (ReactElement reactElement : reaction.reactants)
            stoichiometryVector[state.getOffset(reactElement)] -= 1;

        for (ReactElement reactElement : reaction.products) {
            stoichiometryVector[state.getOffset(reactElement)] += 1;
        }

        return stoichiometryVector;
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

                for (Reaction reaction : continuousReactions) {

                    reaction.updatePropensity(state);
                    double[] v = stoichiometryVectors.get(reaction);

                    for (int i=0; i<state.occupancies.length; i++) {
                        ydot[i] += reaction.currentPropensity * v[i];
                    }
                }
            }
        };


        EventHandler rateShiftHandler = new EventHandler() {

            List<AbstractReaction> reactionsSortedByChangeTimes;
            Double[] changeTimes;

            @Override
            public void init(double t0, double[] y0, double tf) {
                Set<Double> changeTimeSet = new HashSet<>();
                for (AbstractReaction reaction : reactions) {
                    reaction.resetInterval();
                    for (double t : reaction.getAllIntervalEndTimes())
                        changeTimeSet.add(t);
                }

                changeTimes = changeTimeSet.toArray(new Double[0]);

                reactionsSortedByChangeTimes = new ArrayList<>(reactions);
                reactionsSortedByChangeTimes.sort(Comparator.comparingDouble(AbstractReaction::getIntervalEndTime));
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
                double eventTime = reactionsSortedByChangeTimes.get(0).getIntervalEndTime();
                while (reactionsSortedByChangeTimes.get(0).getIntervalEndTime() == eventTime) {
                    AbstractReaction reaction = reactionsSortedByChangeTimes.get(0);

                    if (reaction instanceof PunctualReaction) {
                        PunctualReaction punctualReaction = (PunctualReaction) reaction;

                        System.arraycopy(y, 0, state.occupancies, 0, y.length);
                        state.implementEvent(punctualReaction, false);
                        System.arraycopy(state.occupancies, 0, y, 0, y.length);
                    }

                    reaction.incrementInterval();
                    reactionsSortedByChangeTimes
                            .sort(Comparator.comparingDouble(AbstractReaction::getIntervalEndTime));
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

        double dt = stopTime*relativeStepSizeInput.get().getArrayValue();

        LineageFactory lineageFactory = new LineageFactory();
        Map<ReactElement, List<Lineage>> lineages = new HashMap<>();

        for (PunctualReaction reaction : punctualReactions)
            reaction.resetIntervalToEnd();

        List<PunctualReaction> sortedPunctualReactions = new ArrayList<>(punctualReactions);
        sortedPunctualReactions.sort(Comparator.comparingDouble(PunctualReaction::getIntervalStartTime).reversed());

        while (!sortedPunctualReactions.isEmpty() && sortedPunctualReactions.get(0).getIntervalStartTime()>stopTime) {
            sortedPunctualReactions.get(0).decrementInterval();
            sortedPunctualReactions.sort(Comparator.comparingDouble(PunctualReaction::getIntervalStartTime).reversed());
        }

        double t = stopTime;
        while (t > 0.0) {
            continuousOutputModel.setInterpolatedTime(t);
            System.arraycopy(continuousOutputModel.getInterpolatedState(), 0,
                    state.occupancies, 0, state.occupancies.length);

            while (!sortedPunctualReactions.isEmpty() &&
                    sortedPunctualReactions.get(0).getIntervalStartTime()>t) {
                PunctualReaction reaction = sortedPunctualReactions.get(0);
                reaction.decrementInterval();
                double n = state.implementEvent(reaction, true);
                for (int i=0; i<n; i++) {
                    state.incrementLineages(lineages, reaction, t, lineageFactory, false);
                    state.reverseIncremementState(reaction, 1);
                }
                sortedPunctualReactions.sort(Comparator.comparingDouble(PunctualReaction::getIntervalStartTime).reversed());
            }

            for (Reaction reaction : continuousReactions) {
                reaction.updatePropensity(state);
                double totalInclusionProb =
                        state.getLineageInclusionProbability(lineages, reaction);

                long n = Randomizer.nextPoisson(
                        reaction.currentPropensity*totalInclusionProb*dt);

                for (int i=0; i<n; i++)
                    state.incrementLineages(lineages, reaction, t, lineageFactory,
                            true);
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

        return rootLineages.get(0);
    }

    @Override
    public void log(long sample, PrintStream out) {
        state.resetToInitial();

        out.print("t=0");
        out.print(state);

        double T = maxTimeInput.get().getArrayValue();
        double dt = T/loggingGridSizeInput.get().getArrayValue();
        for (double t=dt; t<stopTime; t += dt) {

            out.print(";");
            out.print("t=" + t);

            continuousOutputModel.setInterpolatedTime(t);
            System.arraycopy(continuousOutputModel.getInterpolatedState(), 0,
                    state.occupancies, 0, state.occupancies.length);

            out.print(state);
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
