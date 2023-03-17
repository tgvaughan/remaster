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

import beast.base.core.BEASTObject;
import beast.base.core.Input;
import beast.base.core.Loggable;
import beast.base.evolution.tree.Node;
import beast.base.evolution.tree.Tree;

import java.io.PrintStream;

public class TypedTreeLogger extends BEASTObject implements Loggable {

    public Input<Tree> treeInput = new Input<>("tree",
            "Tree to log.", Input.Validate.REQUIRED);

    public Input<Boolean> removeSingletonsInput = new Input<>(
            "removeSingletonNodes",
            "Remove nodes with exactly one parent and one child. " +
                    "(Default false.)",
            false);

    Tree tree;

    @Override
    public void initAndValidate() {
        tree = treeInput.get();
    }

    @Override
    public void init(PrintStream out) {
        tree.init(out);
    }

    Node getSingletonFreeTree(Node root) {

        while (root.getChildren().size() == 1) {
            root = root.getChild(0);
        }

        Node newRoot = new Node();
        newRoot.setHeight(root.getHeight());
        newRoot.metaDataString = root.metaDataString;

        if (root.isLeaf()) {
            newRoot.setNr(root.getNr());
            newRoot.setID(newRoot.getID());
        }

        for (Node child : root.getChildren())
            newRoot.addChild(getSingletonFreeTree(child));

        return newRoot;
    }

    @Override
    public void log(long sample, PrintStream out) {
        out.print("tree STATE_" + sample + " = ");
        // Don't sort, this can confuse CalculationNodes relying on the tree

        Node root = removeSingletonsInput.get()
                ? getSingletonFreeTree(tree.getRoot())
                : tree.getRoot();

        final int[] dummy = new int[1];
        final String newick = root.toSortedNewick(dummy, true);
        out.print(newick);
        out.print(";");
    }

    @Override
    public void close(PrintStream out) {
        tree.close(out);
    }
}
