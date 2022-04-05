package remaster;

import beast.evolution.tree.Node;

public class Lineage extends Node {
    public ReactElement reactElement;

    public Lineage(ReactElement reactElement, double baseTime) {
        super();
        this.reactElement = reactElement;
        setHeight(baseTime);
    }
}
