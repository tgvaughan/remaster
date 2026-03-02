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

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import remaster.parsers.ConditionGrammarLexer;
import remaster.parsers.ConditionGrammarParser;
import remaster.parsers.ConditionVisitor;
import remaster.parsers.ContinuousConditionVisitor;

public class BDCondition {

    ParseTree parseTree;
    ConditionVisitor visitor;
    ContinuousConditionVisitor continuousVisitor;

    public BDCondition(String conditionString, BDTrajectoryState state) {

        CharStream input = CharStreams.fromString(conditionString);
        ConditionGrammarLexer lexer = new ConditionGrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ConditionGrammarParser parser = new ConditionGrammarParser(tokens);
        parseTree = parser.expression();
        visitor = new ConditionVisitor(state);
        continuousVisitor = new ContinuousConditionVisitor(state);
    }

    public boolean isMet() {
        return visitor.visit(parseTree)[0] != 0.0;
    }

    public double switchFunction() {
        return continuousVisitor.visit(parseTree)[0];
    }
}
