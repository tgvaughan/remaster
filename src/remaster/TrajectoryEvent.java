package remaster;

/**
 * Events produced by stochastic trajectories.
 */
public class TrajectoryEvent {
    public double time;
    public double multiplicity;
    public AbstractReaction reaction;

    public TrajectoryEvent(double time, AbstractReaction reaction, double multiplicity) {
        this.time = time;
        this.reaction = reaction;
        this.multiplicity = multiplicity;
    }

    @Override
    public String toString() {
        return reaction.toString() + " at t=" + time
                + " (multiplicity " + multiplicity + ")";
    }
}
