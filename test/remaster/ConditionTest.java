package remaster;

import beast.base.core.Function;
import beast.base.inference.parameter.RealParameter;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ConditionTest {

    @Test
    public void testContinuous() {

        List<Function> popFuncs = new ArrayList<>();
        RealParameter X = new RealParameter("100.0");
        X.setID("X");
        popFuncs.add(X);
        RealParameter Y = new RealParameter("50.0");
        Y.setID("Y");
        popFuncs.add(Y);

        List<Function> sampFuncs = new ArrayList<>();
        RealParameter samp = new RealParameter("0.0");
        samp.setID("samp");
        sampFuncs.add(samp);

        TrajectoryState state = new TrajectoryState(popFuncs, sampFuncs);

        try (PrintStream ps = new PrintStream("out.txt")) {
            ps.println("x\tf");
            Condition cond = new Condition("(X < 50) || (X > 70) && (Y == 50)", state);
            for (double x=25; x<100; x+=0.1) {
                state.occupancies[state.getOffset(new ReactElement("X", 0))] = x;
                ps.println(x + "\t" + cond.switchFunction());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
