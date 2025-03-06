/*
 * Copyright (c) 2023 ETH Zurich
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
import beast.base.util.GammaFunction;
import beast.base.util.Randomizer;

public class Util {

    /**
     * Log binomial coefficient allowing floating point arguments.
     *
     * @param n number of elements to choose from
     * @param k number of elements to choose
     * @return log of the corresponding binomial coefficient
     */
    public static double logChoose(double n, double k) {
        return GammaFunction.lnGamma(n + 1.0) - GammaFunction.lnGamma(k + 1.0)
                - GammaFunction.lnGamma(n - k + 1.0);
    }

    /**
     * Draw sample from a binomial distribution.
     *
     * @param N number of trials
     * @param p success probability of each trial
     * @return number of successes
     */
    public static double nextBinomial(double N, double p) {
        double logP = N * Math.log(1 - p);
        double C = Math.exp(logP);
        double logf = Math.log(p / (1 - p));

        int n = 0;
        double u = Randomizer.nextDouble();
        while (u > C) {
            n += 1;
            logP += logf + Math.log(N - n + 1) - Math.log(n);
            C += Math.exp(logP);
        }

        return n;
    }


    /**
     * Transform tree with given root into new tree in which singleton
     * nodes (one parent, one child) have been removed.
     * @param root Root node of tree to transform
     * @return Root node of transformed tree
     */
    public static Node getSingletonFreeTree(Node root) {
        Node newRoot = getSingletonFreeTreeRecurse(root);
        renumberInternalNodes(newRoot, root.getLeafNodeCount());

        return newRoot;
    }

    /**
     * Helper function for getSingletonFreeTree().  Recursively creates a
     * new tree identical to the input tree but with the singleton nodes
     * removed.
     *
     * @param root root of tree to transform
     * @return root of newly transformed tree.
     */
    private static Node getSingletonFreeTreeRecurse(Node root) {

        while (root.getChildren().size() == 1) {
            root = root.getChild(0);
        }

        Node newRoot = new Node();
        newRoot.setHeight(root.getHeight());
        newRoot.metaDataString = root.metaDataString;

        if (root.isLeaf()) {
            newRoot.setNr(root.getNr());
            newRoot.setID(root.getID());
        }

        for (Node child : root.getChildren())
            newRoot.addChild(getSingletonFreeTreeRecurse(child));

        return newRoot;
    }

    /**
     * Helper function for getSingletonFreeTree().  Recursively
     * renumbers internal nodes so that they are numbered between
     * N and 2N-2.
     *
     * @param root root of tree with node numbers to adjust
     * @param nextNr number to be assigned to next internal node. Initialise
     *               to the number of leaf nodes.
     * @return updated nextNr.  (At the top level the return value can be
     * discarded.  Its value is used by the recursive calls to this function.)
     */
    private static int renumberInternalNodes(Node root, int nextNr) {

        if (root.isLeaf())
            return nextNr;

        for (Node child : root.getChildren()) {
            nextNr = renumberInternalNodes(child, nextNr);
        }

        root.setNr(nextNr);

        return nextNr + 1;
    }
}
