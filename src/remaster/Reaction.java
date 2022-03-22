package remaster;

import beast.core.BEASTObject;
import beast.core.Function;
import beast.core.Input;
import beast.core.util.Log;
import beast.evolution.tree.Node;
import beast.math.Binomial;
import beast.util.Randomizer;
import com.google.common.collect.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import remaster.parsers.ReactionGrammarBaseListener;
import remaster.parsers.ReactionGrammarLexer;
import remaster.parsers.ReactionGrammarParser;

import java.util.*;

public class Reaction extends BEASTObject {

    public Input<Function> rateInput = new Input<>("rate",
            "Per-configuration rate constant.");

    public Input<String> reactionStringInput = new Input<>("value",
            "Reaction string.", Input.Validate.REQUIRED);

    Multiset<ReactElement> reactants, products;

    List<ReactElement> parents;
    List<Multiset<ReactElement>> children;

    @Override
    public void initAndValidate() {
        parseReactionString(reactionStringInput.get());
    }

    /**
     * Parse reaction string and record reactants and products
     * in the Multisets reactants and products.
     *
     * @param reactionString reaction string
     */
    private void parseReactionString(String reactionString) {
        CharStream rsInput = CharStreams.fromString(reactionString);

        // Custom parser/lexer error listener
        BaseErrorListener errorListener = new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer,
                                    Object offendingSymbol,
                                    int line, int charPositionInLine,
                                    String msg, RecognitionException e) {
                throw new RuntimeException("Error parsing character " +
                        charPositionInLine + " of reaction " +
                        "string '" + reactionString + "': " + msg);
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

        parents = new ArrayList<>();
        children = new ArrayList<>();

        parseTreeWalker.walk(new ReactionGrammarBaseListener() {
            @Override
            public void exitReaction(ReactionGrammarParser.ReactionContext ctx) {

                Map<String, Integer> parentIDs = new HashMap<>();

                // Process reactants
                for (ReactionGrammarParser.PopelContext popelContext : ctx.reactants().popsum().popel()) {
                    String name = popelContext.popname().getText();
                    int idx = popelContext.loc() == null ? 0
                            : Integer.parseInt(popelContext.loc().locidx().getText());
                    int factor = popelContext.factor() == null ? 1
                            : Integer.parseInt(popelContext.factor().getText());
                    ReactElement el = new ReactElement(name, idx);
                    reactants.add(el, factor);

                    String reactID = popelContext.id() == null
                            ? null : popelContext.id().getText().intern();

                    for (int i=0; i<factor; i++) {
                        parents.add(el);
                        children.add(HashMultiset.create());

                        if (reactID != null) {
                            if (parentIDs.containsKey(reactID))
                                throw new IllegalStateException("In reaction '"
                                        + reactionString +"' reactants cannot share an ID.");
                            parentIDs.put(reactID, parents.size() - 1);
                        }
                    }
                }

                // Process products
                for (ReactionGrammarParser.PopelContext popelContext : ctx.products().popsum().popel()) {
                    String name = popelContext.popname().getText();
                    int idx = popelContext.loc() == null ? 0
                            : Integer.parseInt(popelContext.loc().locidx().getText());
                    int factor = popelContext.factor() == null ? 1
                            : Integer.parseInt(popelContext.factor().getText());
                    ReactElement el = new ReactElement(name, idx);
                    products.add(el, factor);

                    String reactID = popelContext.id() == null
                            ? null : popelContext.id().getText().intern();

                    int parentIndex;
                    if (reactID != null) {
                        if (!parentIDs.containsKey(reactID))
                            throw new IllegalStateException("In reaction '" + reactionString + "' the product ID " +
                                    "'" + reactID + "' is not associated with a reactant.");

                        parentIndex = parentIDs.get(reactID);
                    } else {
                        // Find first parent with same population
                        parentIndex = 0;
                        while (parentIndex < parents.size() && !parents.get(parentIndex).name.equals(el.name))
                            parentIndex += 1;
                    }

                    if (parentIndex < parents.size())
                        children.get(parentIndex).add(el, factor);
                }
            }
        }, reactionStringParseTree);
    }

    Set<String> samplePopNames;
    public boolean producesSamples = false;

    public void markSamples(TrajectoryState state) {
        samplePopNames = state.samplePopNames;

        for (ReactElement element : products.elementSet()) {
            if (samplePopNames.contains(element.name)) {
                producesSamples = true;
                return;
            }
        }

        producesSamples = true;
    }

    public boolean isValid(TrajectoryState state) {
        for (ReactElement reactElement : Sets.union(reactants.elementSet(), products.elementSet())) {
            if (!state.hasPopNamed(reactElement.name)) {
                Log.err("Reaction " + getID() + " (" + this + ") contains unknown element '" + reactElement.name + "'.");
                return false;
            }
        }

        for (ReactElement reactElement : reactants.elementSet()) {
            if (state.hasSamplePopNamed(reactElement.name)) {
                Log.err("Reaction " + getID() + " (" + this + ") contains sample population '" + reactElement.name + "' as reactant.");
                return false;
            }
        }

        return true;
    }


    /**
     * Update current propensity using the provided state.
     *
     * @param state trajectory state
     * @return calculated propensity
     */
    public double updatePropensity(TrajectoryState state) {
        currentPropensity = rateInput.get().getArrayValue();
        for (ReactElement reactElement : reactants.elementSet()) {
            currentPropensity *= Binomial.choose(state.get(reactElement.name)[reactElement.idx],
                    reactants.count(reactElement));
        }

        return currentPropensity;
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
    public void incrementState(TrajectoryState state) {
        for (ReactElement reactElement : reactants.elementSet())
            state.get(reactElement.name)[reactElement.idx] -= reactants.count(reactElement);

        for (ReactElement reactElement : products.elementSet())
            state.get(reactElement.name)[reactElement.idx] += products.count(reactElement);
    }

    /**
     * Increment lineage state according to reaction.
     */
    public void incrementLineages(Map<ReactElement, List<Node>> lineages, TrajectoryState state) {
        if (lineages.isEmpty() && !producesSamples)
            return;

        // We're iterating over the products here anyway.  THIS is where we choose
        // which products go with which lineages:

        double logP_noEffect = 0.0;
        for (ReactElement el : products.elementSet()) {
            if (!lineages.containsKey(el))
                continue;

            logP_noEffect += Util.logChoose(state.get(el.name)[el.idx]-lineages.get(el).size(),
                    products.count(el));
        }

        if (logP_noEffect == 0.0
                || (logP_noEffect > Double.NEGATIVE_INFINITY
                && Randomizer.nextDouble() > Math.exp(logP_noEffect)))
            return;



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

    // Main method for debugging
    public static void main(String[] args) {

        Reaction reaction = new Reaction();
        reaction.initByName("rate", 0.01,
                "value", "S + I -> 2I");
    }

}
