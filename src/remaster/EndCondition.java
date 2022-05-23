package remaster;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import remaster.parsers.EndConditionGrammarLexer;
import remaster.parsers.EndConditionGrammarParser;
import remaster.parsers.EndConditionVisitor;

public class EndCondition {

    ParseTree parseTree;
    EndConditionVisitor visitor;

    public EndCondition(String conditionString, TrajectoryState state) {

        CharStream ecInput = CharStreams.fromString(conditionString);
        EndConditionGrammarLexer ecLexer = new EndConditionGrammarLexer(ecInput);
        CommonTokenStream ecTokens = new CommonTokenStream(ecLexer);
        EndConditionGrammarParser ecParser = new EndConditionGrammarParser(ecTokens);
        parseTree = ecParser.expression();
        visitor = new EndConditionVisitor(state);
    }

    public boolean isMet() {
        return visitor.visit(parseTree)[0] != 0.0;
    }
}
