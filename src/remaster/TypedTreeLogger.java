package remaster;

import beast.core.BEASTObject;
import beast.core.Input;
import beast.core.Loggable;
import beast.evolution.tree.Node;
import beast.evolution.tree.Tree;

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
