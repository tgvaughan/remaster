/*
 * Copyright (c) 2023-2025 ETH Zurich
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

import beast.base.core.Input;
import beast.base.core.Log;
import beast.base.evolution.tree.Node;
import beast.base.evolution.tree.Tree;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class SimulatedTree extends Tree {

    public Input<AbstractTrajectory> trajectoryInput = new Input<>("trajectory",
            "Trajectory to use as basis of tree simulation.",
            Input.Validate.REQUIRED);

    public Input<Integer> maxRetriesInput = new Input<>("maxRetries",
            "Maximum number of times to retry tree simulation after failure " +
                    "to simulate tree with at least one tip and exactly one " +
                    "root lineage.", 10);

    public Input<Boolean> removeSingletonsInput = new Input<>(
            "removeSingletonNodes",
            "Remove nodes with exactly one parent and one child. " +
                    "(Default false.)",
            false);

    AbstractTrajectory trajectory;
    int maxRetries;

    @Override
    public void initAndValidate() {
        trajectory = trajectoryInput.get();
        maxRetries = maxRetriesInput.get();

        super.initAndValidate();

        doSimulation();
    }

    public void doSimulation() {

        Node root = null;

        int retries = maxRetries;
        while (root == null && retries >= 0) {
            try {
                root = trajectory.simulateTree();
            } catch (AbstractBDTrajectory.SimulationFailureException e) {
                Log.err.print("Tree simulation error: " + e.getMessage());
                retries -= 1;

                if (retries >= 0) {
                    Log.err.println(" Retrying.");
                    trajectory.initAndValidate();
                } else
                    Log.err.println();
            }
        }

        if (root == null) {
            Log.err.println("Failed to simulate tree with at least one leaf " +
                    "and exactly one root lineage. (maxRetries = " +
                    maxRetries + ")");
            System.exit(1);
        }

        if (removeSingletonsInput.get())
            root = Util.getSingletonFreeTree(root);

        nonBinaryAssignFromWithoutID(new Tree(root));

        // Recover taxon set
        if (m_taxonset.get() != null) {
            if (getLeafNodeCount() != m_taxonset.get().getTaxonCount())
                throw new IllegalArgumentException("Number of leaves in simulated tree does not match the number of provided taxa.");

            for (int i=0; i<getLeafNodeCount(); i++) {
                getNode(i).setID(m_taxonset.get().getTaxonId(i));
            }

        }

    }

    /**
     * Duplicates copies another tree to this tree.  In contrast to
     * the Tree::assignFrom(), this method does not assume that
     * the tree being copied is binary.
     *
     * @param otherTree tree to copy
     */
    private void nonBinaryAssignFromWithoutID(Tree otherTree) {
        final RemasterNode[] theseNodes = new RemasterNode[otherTree.getNodeCount()];
        for (int i = 0; i < otherTree.getNodeCount(); i++)
            theseNodes[i] = new RemasterNode();

        RemasterNode rootNode = theseNodes[otherTree.getRoot().getNr()];
        rootNode.assignFromNonBinary(theseNodes, otherTree.getRoot());

        root = rootNode;
        root.setParent(null);
        nodeCount = theseNodes.length;
        internalNodeCount = -1;
        leafNodeCount = -1;
        initArrays();
    }

    /**
     * Helper subclass needed to access protected members of Node.
     */
    private static class RemasterNode extends Node {

        public void assignFromNonBinary(RemasterNode[] theseNodes, Node otherNode) {
            height = otherNode.getHeight();
            labelNr = otherNode.getNr();
            metaDataString = otherNode.metaDataString;
            lengthMetaDataString = otherNode.lengthMetaDataString;
            metaData = new TreeMap<>();
            for (String key : otherNode.getMetaDataNames())
                metaData.put(key, otherNode.getMetaData(key));
            lengthMetaData = new TreeMap<>();
            for (String key : otherNode.getLengthMetaDataNames())
                lengthMetaData.put(key, otherNode.getLengthMetaData(key));
            parent = null;
            setID(otherNode.getID());

            for (Node otherChild : otherNode.getChildren()) {
                RemasterNode thisChild = theseNodes[otherChild.getNr()];
                children.add(thisChild);
                thisChild.assignFromNonBinary(theseNodes, otherChild);
                thisChild.setParentOnly(this);
            }
        }

        public void setParentOnly(Node newParent) {
            parent = newParent;
        }
    }

    /*
     * Modifications to Tree logging methods to allow correct logging of
     * non-binary trees.
     */

    @Override
    public void init(PrintStream out) {
        Node node = getRoot();
        out.println("#NEXUS\n");
        out.println("Begin taxa;");
        out.println("\tDimensions ntax=" + getLeafNodeCount() + ";");
        out.println("\t\tTaxlabels");
        printTaxaNonBinary(node, out, getLeafNodeCount());
        out.println("\t\t\t;");
        out.println("End;");

        out.println("Begin trees;");
        out.println("\tTranslate");
        printTranslateNonBinary(node, out, getLeafNodeCount());
        out.print(";");
    }

    public static void printTranslateNonBinary(final Node node, final PrintStream out, final int nodeCount) {
        final List<String> translateLines = new ArrayList<>();
        printTranslateNonBinary(node, translateLines, nodeCount);
        Collections.sort(translateLines);
        for (final String line : translateLines) {
            out.println(line);
        }
    }

    /**
     * need this helper so that we can sort list of entries *
     */
    static void printTranslateNonBinary(Node node, List<String> translateLines, int nodeCount) {
        if (node.isLeaf()) {
            String line = String.format("\t\t%6d ", node.getNr() + taxaTranslationOffset);
            if (node.getID().indexOf(' ') > 0) {
                char c = node.getID().charAt(0);
                if (c == '\"' || c == '\'') {
                    line += node.getID();
                } else {
                    line += '\"' + node.getID() + "\"";
                }
            } else {
                line += node.getID();
            }
            if (node.getNr() < nodeCount) {
                line += ",";
            }
            translateLines.add(line);
        } else {
            for (Node child : node.getChildren())
                printTranslateNonBinary(child, translateLines, nodeCount);
        }
    }

    public static void printTaxaNonBinary(final Node node, final PrintStream out, final int nodeCount) {
        final List<String> translateLines = new ArrayList<>();
        printTranslateNonBinary(node, translateLines, nodeCount);
        Collections.sort(translateLines);
        for (String line : translateLines) {
            line = line.substring(line.indexOf(" ", 7)).replace(',', ' ').trim();
            out.println("\t\t\t" + line);
        }
    }

}
