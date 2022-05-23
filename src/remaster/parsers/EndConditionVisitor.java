package remaster.parsers;

import org.antlr.v4.runtime.tree.ParseTree;
import remaster.TrajectoryState;

import java.util.Arrays;

public class EndConditionVisitor extends EndConditionGrammarBaseVisitor<Double[]> {

    TrajectoryState state;

    public EndConditionVisitor(TrajectoryState state) {
        this.state = state;
    }

    @Override
    public Double[] visitPop(EndConditionGrammarParser.PopContext ctx) {
        Double[] res = state.get(ctx.popname().getText());
        if (ctx.loc() != null)
            return new Double[] {res[Integer.parseInt(ctx.loc().locidx().getText())]};

        return res;
    }

    @Override
    public Double[] visitNumber(EndConditionGrammarParser.NumberContext ctx) {
        return new Double[] {Double.valueOf(ctx.val.getText())};
    }

    @Override
    public Double[] visitEquality(EndConditionGrammarParser.EqualityContext ctx) {
        Double [] left = visit(ctx.expression(0));
        Double [] right = visit(ctx.expression(1));

        Double [] res = new Double[Math.max(left.length, right.length)];
        for (int i=0; i<res.length; i++) {
            switch (ctx.op.getType()) {
                case EndConditionGrammarParser.GT:
                    res[i] = left[i%left.length] > right[i%right.length] ? 1.0 : 0.0;
                    break;
                case EndConditionGrammarParser.LT:
                    res[i] = left[i%left.length] < right[i%right.length] ? 1.0 : 0.0;
                    break;
                case EndConditionGrammarParser.GE:
                    res[i] = left[i%left.length] >= right[i%right.length] ? 1.0 : 0.0;
                    break;
                case EndConditionGrammarParser.LE:
                    res[i] = left[i%left.length] <= right[i%right.length] ? 1.0 : 0.0;
                    break;
                case EndConditionGrammarParser.EQ:
                    res[i] = left[i%left.length].equals(right[i%right.length]) ? 1.0 : 0.0;
                    break;
                case EndConditionGrammarParser.NE:
                    res[i] = left[i%left.length].equals(right[i%right.length]) ? 0.0 : 1.0;
                    break;
            }
        }

        return res;
    }

    @Override
    public Double[] visitBooleanOp(EndConditionGrammarParser.BooleanOpContext ctx) {
        Double [] left = visit(ctx.expression(0));
        Double [] right = visit(ctx.expression(1));

        Double [] res = new Double[Math.max(left.length, right.length)];
        for (int i=0; i<res.length; i++) {
            switch (ctx.op.getType()) {
                case EndConditionGrammarParser.AND:
                    res[i] = ((left[i%left.length] > 0) && (right[i%right.length] > 0)) ? 1.0 : 0.0;
                    break;
                case EndConditionGrammarParser.OR:
                    res[i] = ((left[i%left.length] > 0) || (right[i%right.length] > 0)) ? 1.0 : 0.0;
                    break;
            }
        }

        return res;
    }

    @Override
    public Double[] visitBracketed(EndConditionGrammarParser.BracketedContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Double[] visitUnaryOp(EndConditionGrammarParser.UnaryOpContext ctx) {

        Double [] arg = visit(ctx.expression());
        Double [] res = null;

        switch(ctx.op.getType()) {
            case EndConditionGrammarParser.SUM:
                res = new Double[1];
                res[0] = 0.0;
                for (Double el : arg)
                    res[0] += el;
                break;

            case EndConditionGrammarParser.MIN:
                res = new Double[1];
                res[0] = arg[0];
                for (int i=1; i<arg.length; i++) {
                    if (arg[i]<res[0])
                        res[0] = arg[i];
                }
                break;

            case EndConditionGrammarParser.MAX:
                res = new Double[1];
                res[0] = arg[0];
                for (int i=1; i<arg.length; i++) {
                    if (arg[i]>res[0])
                        res[0] = arg[i];
                }
                break;
        }

        return res;
    }
}
