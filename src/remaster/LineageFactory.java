package remaster;

import beast.base.evolution.tree.Node;

public class LineageFactory {
    double timeOfFinalSample;
    int nextLeafNr;
    boolean finalSampleSeen = false;

    private Lineage createLineage(ReactElement lineageReactEl, double time) {
        if (!finalSampleSeen) {
            timeOfFinalSample = time;
            finalSampleSeen = true;
        }

        return new Lineage(lineageReactEl, timeOfFinalSample - time);
    }

    public Lineage createSample(ReactElement lineageReactEl, double time) {
        Lineage lineage = createLineage(lineageReactEl, time);
        lineage.setNr(nextLeafNr);
        lineage.setID("leaf_" + nextLeafNr);

        nextLeafNr += 1;

        return lineage;
    }

    public Lineage createInternal(ReactElement lineageReactEl, double time) {
        Lineage lineage = createLineage(lineageReactEl, time);
        lineage.setNr(-1);

        return lineage;
    }

    public void numberInternals(Node root) {
        if (!root.isLeaf()) {
            for (Node child : root.getChildren())
                numberInternals(child);

            root.setNr(nextLeafNr);

            nextLeafNr += 1;
        }
    }

}
