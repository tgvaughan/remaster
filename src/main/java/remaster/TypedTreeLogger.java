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

    public Input<Boolean> noLabelsInput = new Input<>(
            "noLabels", "Avoid including taxa block and translate " +
            "command in output tree file.  These elements can cause problems " +
            "for external software when the number of tips vary between trees.",
            false);

    Tree tree;

    @Override
    public void initAndValidate() {
        tree = treeInput.get();
    }

    @Override
    public void init(PrintStream out) {
        if (noLabelsInput.get()) {
            out.println("#NEXUS\n");
            out.println("Begin trees;");
        } else {
            tree.init(out);
        }
    }

    @Override
    public void log(long sample, PrintStream out) {
        out.print("tree STATE_" + sample + " = ");
        // Don't sort, this can confuse CalculationNodes relying on the tree

        Node root = removeSingletonsInput.get()
                ? Util.getSingletonFreeTree(tree.getRoot())
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
