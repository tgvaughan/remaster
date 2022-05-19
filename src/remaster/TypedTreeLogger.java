package remaster;

import beast.core.BEASTObject;
import beast.core.Input;
import beast.core.Loggable;
import beast.evolution.tree.Tree;

import java.io.PrintStream;

public class TypedTreeLogger extends BEASTObject implements Loggable {

    public Input<Tree> treeInput = new Input<Tree>("tree",
            "Tree to log.", Input.Validate.REQUIRED);

    Tree tree;

    @Override
    public void initAndValidate() {
        tree = treeInput.get();
    }

    @Override
    public void init(PrintStream out) {
        tree.init(out);
    }

    @Override
    public void log(long sample, PrintStream out) {
        out.print("tree STATE_" + sample + " = ");
        // Don't sort, this can confuse CalculationNodes relying on the tree
        final int[] dummy = new int[1];
        final String newick = tree.getRoot().toSortedNewick(dummy, true);
        out.print(newick);
        out.print(";");
    }

    @Override
    public void close(PrintStream out) {
        tree.close(out);
    }
}
