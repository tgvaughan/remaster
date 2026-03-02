open module remaster {
    requires beast.pkgmgmt;
    requires beast.base;
    requires org.apache.commons.statistics.distribution;
    requires org.antlr.antlr4.runtime;
    requires com.google.common;
    requires commons.math3;

    exports remaster;

    provides beast.base.core.BEASTInterface with
            remaster.Reaction,
            remaster.Simulator,
            remaster.TypedTreeLogger,
            remaster.PrunedTree,
            remaster.StochasticTrajectoryStatsLogger,
            remaster.PunctualReaction,
            remaster.SimulatedTree,
            remaster.StochasticTrajectory,
            remaster.DeterministicTrajectory,
            remaster.CoalescentTrajectory;
}