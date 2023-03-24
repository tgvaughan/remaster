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

import beast.base.evolution.tree.Node;

public class LineageFactory {
    int nextLeafNr = 0;
    double latestTime = 0;

    public Lineage createSample(ReactElement lineageReactEl, double time) {
        Lineage lineage = new Lineage(lineageReactEl, time);
        lineage.setNr(nextLeafNr);
        lineage.setID("leaf_" + nextLeafNr);

        nextLeafNr += 1;

        if (time > latestTime)
            latestTime = time;

        return lineage;
    }

    public Lineage createInternal(ReactElement lineageReactEl, double time) {
        Lineage lineage = new Lineage(lineageReactEl, time);
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

    public void computeAgesFromTimes(Node root) {
        root.setHeight(latestTime - root.getHeight());
        for (Node child :root.getChildren())
            computeAgesFromTimes(child);
    }

}
