package remaster;

import beast.evolution.tree.Node;

public class Lineage extends Node {
    public ReactElement reactElement;

    public Lineage(ReactElement reactElement, double height) {
        super();
        this.reactElement = reactElement;
        setHeight(height);
        metaDataString = "type=\"" + reactElement.toString() + "\"";
    }
}
