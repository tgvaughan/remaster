package remaster.parsers;

import remaster.TrajectoryState;

public class ConditionVisitor extends ConditionGrammarBaseVisitor<Double[]> {

    TrajectoryState state;

    public ConditionVisitor(TrajectoryState state) {
        this.state = state;
    }

    @Override
    public Double[] visitPop(ConditionGrammarParser.PopContext ctx) {
        if (ctx.loc() != null) {
            return new Double[]{state.get(ctx.popname().getText().intern(),
                    Integer.parseInt(ctx.loc().locidx().getText()))};
        } else {
            Double[] res = state.getArray(ctx.popname().getText().intern());
            return res;
        }

    }

    @Override
    public Double[] visitNumber(ConditionGrammarParser.NumberContext ctx) {
        return new Double[] {Double.valueOf(ctx.val.getText())};
    }

    @Override
    public Double[] visitMulDiv(ConditionGrammarParser.MulDivContext ctx) {
        Double [] left = visit(ctx.expression(0));
        Double [] right = visit(ctx.expression(1));

        Double [] res = new Double[Math.max(left.length, right.length)];
        for (int i=0; i<res.length; i++) {
            switch (ctx.op.getType()) {
                case ConditionGrammarParser.MUL:
                    res[i] = left[i%left.length] * right[i%right.length];
                    break;
                case ConditionGrammarParser.DIV:
                    res[i] = left[i%left.length] / right[i%right.length];
                    break;
            }
        }

        return res;
    }

    @Override
    public Double[] visitAddSub(ConditionGrammarParser.AddSubContext ctx) {
        Double [] left = visit(ctx.expression(0));
        Double [] right = visit(ctx.expression(1));

        Double [] res = new Double[Math.max(left.length, right.length)];
        for (int i=0; i<res.length; i++) {
            switch (ctx.op.getType()) {
                case ConditionGrammarParser.ADD:
                    res[i] = left[i%left.length] + right[i%right.length];
                    break;
                case ConditionGrammarParser.SUB:
                    res[i] = left[i%left.length] - right[i%right.length];
                    break;
            }
        }

        return res;
    }

    @Override
    public Double[] visitEquality(ConditionGrammarParser.EqualityContext ctx) {
        Double [] left = visit(ctx.expression(0));
        Double [] right = visit(ctx.expression(1));

        Double [] res = new Double[Math.max(left.length, right.length)];
        for (int i=0; i<res.length; i++) {
            switch (ctx.op.getType()) {
                case ConditionGrammarParser.GT:
                    res[i] = left[i%left.length] > right[i%right.length] ? 1.0 : 0.0;
                    break;
                case ConditionGrammarParser.LT:
                    res[i] = left[i%left.length] < right[i%right.length] ? 1.0 : 0.0;
                    break;
                case ConditionGrammarParser.GE:
                    res[i] = left[i%left.length] >= right[i%right.length] ? 1.0 : 0.0;
                    break;
                case ConditionGrammarParser.LE:
                    res[i] = left[i%left.length] <= right[i%right.length] ? 1.0 : 0.0;
                    break;
                case ConditionGrammarParser.EQ:
                    res[i] = left[i%left.length].equals(right[i%right.length]) ? 1.0 : 0.0;
                    break;
                case ConditionGrammarParser.NE:
                    res[i] = left[i%left.length].equals(right[i%right.length]) ? 0.0 : 1.0;
                    break;
            }
        }

        return res;
    }

    @Override
    public Double[] visitBooleanOp(ConditionGrammarParser.BooleanOpContext ctx) {
        Double [] left = visit(ctx.expression(0));
        Double [] right = visit(ctx.expression(1));

        Double [] res = new Double[Math.max(left.length, right.length)];
        for (int i=0; i<res.length; i++) {
            switch (ctx.op.getType()) {
                case ConditionGrammarParser.AND:
                    res[i] = ((left[i%left.length] > 0) && (right[i%right.length] > 0)) ? 1.0 : 0.0;
                    break;
                case ConditionGrammarParser.OR:
                    res[i] = ((left[i%left.length] > 0) || (right[i%right.length] > 0)) ? 1.0 : 0.0;
                    break;
            }
        }

        return res;
    }

    @Override
    public Double[] visitBracketed(ConditionGrammarParser.BracketedContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Double[] visitUnaryOp(ConditionGrammarParser.UnaryOpContext ctx) {

        Double [] arg = visit(ctx.expression());
        Double [] res = null;

        switch(ctx.op.getType()) {
            case ConditionGrammarParser.SUM:
                res = new Double[1];
                res[0] = 0.0;
                for (Double el : arg)
                    res[0] += el;
                break;

            case ConditionGrammarParser.MIN:
                res = new Double[1];
                res[0] = arg[0];
                for (int i=1; i<arg.length; i++) {
                    if (arg[i]<res[0])
                        res[0] = arg[i];
                }
                break;

            case ConditionGrammarParser.MAX:
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
