package remaster;

import beast.core.BEASTObject;
import beast.core.Function;
import beast.core.Input;
import beast.core.util.Log;
import beast.math.Binomial;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import remaster.parsers.ReactionGrammarBaseListener;
import remaster.parsers.ReactionGrammarLexer;
import remaster.parsers.ReactionGrammarParser;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Reaction extends BEASTObject {

    public Input<Function> rateInput = new Input<>("rate",
            "Per-configuration rate constant.");

    public Input<String> reactionStringInput = new Input<>("value",
            "Reaction string.", Input.Validate.REQUIRED);

    Multiset<ReactElement> reactants, products;

    @Override
    public void initAndValidate() {

        // Parse reaction string
        CharStream rsInput = CharStreams.fromString(reactionStringInput.get());

        // Custom parser/lexer error listener
        BaseErrorListener errorListener = new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer,
                                    Object offendingSymbol,
                                    int line, int charPositionInLine,
                                    String msg, RecognitionException e) {
                throw new RuntimeException("Error parsing character " +
                        charPositionInLine + " of MASTER reaction " +
                        "string: " + msg);
            }
        };

        ReactionGrammarLexer rsLexer = new ReactionGrammarLexer(rsInput);
        rsLexer.removeErrorListeners();
        rsLexer.addErrorListener(errorListener);

        CommonTokenStream rsTokens = new CommonTokenStream(rsLexer);

        ReactionGrammarParser rsParser = new ReactionGrammarParser(rsTokens);
        rsParser.removeErrorListeners();
        rsParser.addErrorListener(errorListener);

        ParseTree reactionStringParseTree = rsParser.reaction();

        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();

        reactants = HashMultiset.create();
        products = HashMultiset.create();

        parseTreeWalker.walk(new ReactionGrammarBaseListener() {
            @Override
            public void exitReaction(ReactionGrammarParser.ReactionContext ctx) {
                for (ReactionGrammarParser.PopelContext popelContext : ctx.reactants().popsum().popel()) {
                    String name = popelContext.popname().getText();
                    int idx = popelContext.loc() == null ? 0
                            : Integer.parseInt(popelContext.loc().locidx().getText());
                    int factor = popelContext.factor() == null ? 1
                            : Integer.parseInt(popelContext.factor().getText());
                    reactants.add(new ReactElement(name, idx), factor);
                }

                for (ReactionGrammarParser.PopelContext popelContext : ctx.products().popsum().popel()) {
                    String name = popelContext.popname().getText();
                    int idx = popelContext.loc() == null ? 0
                            : Integer.parseInt(popelContext.loc().locidx().getText());
                    int factor = popelContext.factor() == null ? 1
                            : Integer.parseInt(popelContext.factor().getText());
                    products.add(new ReactElement(name, idx), factor);
                }
            }
        }, reactionStringParseTree);
    }

    public boolean producesSamples(Set<String> samplePopulations) {
        for (ReactElement element : products.elementSet())
            if (samplePopulations.contains(element.name))
                return true;

        return false;
    }

    /**
     * Update current propensity using the provided state.
     *
     * @param state
     */
    public void updatePropensity(Map<String, Double[]> state) {
        currentPropensity = rateInput.get().getArrayValue();
        for (ReactElement reactElement : reactants.elementSet()) {
            currentPropensity *= Binomial.choose( state.get(reactElement.name)[reactElement.idx],
                    reactants.count(reactElement));
        }
    }

    /**
     * Current propensity.
     */
    public double currentPropensity;

    /**
     * Increment state according to reaction
     *
     * @param state
     */
    public void incrementState(Map<String, Double[]> state) {
        for (ReactElement reactElement : reactants.elementSet())
            state.get(reactElement.name)[reactElement.idx] -= reactants.count(reactElement);

        for (ReactElement reactElement : products.elementSet())
            state.get(reactElement.name)[reactElement.idx] += products.count(reactElement);
    }

    @Override
    public String toString() {
        return getElementSumString(reactants) + " -> " + getElementSumString(products);
    }

    /**
     * Construct string representing a sum of reaction elements. Used by toString().
     *
     * @param elements Multiset containing reaction elements
     * @return generated string
     */
    private String getElementSumString(Multiset<ReactElement> elements) {
        StringBuilder sb = new StringBuilder();

        if (elements.isEmpty())
            sb.append(0);
        else {
            boolean isFirst = true;
            for (ReactElement reactElement : elements.elementSet()) {
                if (isFirst)
                    isFirst = false;
                else
                    sb.append(" + ");

                int count = elements.count(reactElement);
                if (count > 1)
                    sb.append(count);

                sb.append(reactElement.name).append("[").append(reactElement.idx).append("]");
            }
        }

        return sb.toString();
    }

    /**
     * Class for reactants and products of reaction
     */
    public static class ReactElement {
        String name;
        int idx;

        public ReactElement(String name, int idx) {
            this.name = name.intern();
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "ReactElement{" +
                    "name='" + name + '\'' +
                    ", idx=" + idx +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ReactElement that = (ReactElement) o;
            return idx == that.idx && name.equals(that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, idx);
        }
    }

    // Main method for debugging
    public static void main(String[] args) {

        Reaction reaction = new Reaction();
        reaction.initByName("rate", 0.01,
                "value", "S + I -> 2I");
    }

}
