package remaster;

public class TrajectoryEvent {
    public double time;
    Reaction reaction;

    public TrajectoryEvent(double time, Reaction reaction) {
        this.time = time;
        this.reaction = reaction;
    }
}
