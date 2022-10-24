package remaster;

import beast.base.evolution.tree.Node;

/**
 * A class of objects representing "typed" lineages.
 * Additionally, the constructor records the type in the metaDataString field,
 * for logging purposes.
 */
public class Lineage extends Node {
    public ReactElement reactElement;

    public Lineage(ReactElement reactElement, double height) {
        super();
        this.reactElement = reactElement;
        setHeight(height);
        metaDataString = "type=\"" + reactElement + "\"";
    }
}
