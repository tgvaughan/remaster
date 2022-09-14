package remaster;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import remaster.parsers.ConditionGrammarLexer;
import remaster.parsers.ConditionGrammarParser;
import remaster.parsers.ConditionVisitor;
import remaster.parsers.ContinuousConditionVisitor;

public class Condition {

    ParseTree parseTree;
    ConditionVisitor visitor;
    ContinuousConditionVisitor continuousVisitor;

    public Condition(String conditionString, TrajectoryState state) {

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
