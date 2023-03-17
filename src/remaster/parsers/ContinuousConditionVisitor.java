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

package remaster.parsers;

import remaster.TrajectoryState;

public class ContinuousConditionVisitor extends ConditionVisitor {

    public ContinuousConditionVisitor(TrajectoryState state) {
        super(state);
    }

    @Override
    public Double[] visitEquality(ConditionGrammarParser.EqualityContext ctx) {
        Double [] left = visit(ctx.expression(0));
        Double [] right = visit(ctx.expression(1));

        if ((ctx.op.getType() == ConditionGrammarParser.EQ)
        || (ctx.op.getType() == ConditionGrammarParser.NE)) {
            throw new IllegalArgumentException("Cannot use == or != operators " +
                    "to condition on the state of populations in continuous simulations.");
        }

        Double [] res = new Double[Math.max(left.length, right.length)];
        for (int i=0; i<res.length; i++) {
            switch (ctx.op.getType()) {
                case ConditionGrammarParser.GE:
                case ConditionGrammarParser.GT:
                    res[i] = left[i%left.length] - right[i%right.length];
                    break;
                case ConditionGrammarParser.LE:
                case ConditionGrammarParser.LT:
                    res[i] = right[i%right.length] - left[i%left.length];
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
            double l = left[i%left.length];
            double r = right[i%right.length];
            switch (ctx.op.getType()) {
                case ConditionGrammarParser.AND:
                    res[i] = Math.min(l, r);
                    break;
                case ConditionGrammarParser.OR:
                    res[i] = Math.max(l, r);
                    break;
            }
        }

        return res;
    }
}
