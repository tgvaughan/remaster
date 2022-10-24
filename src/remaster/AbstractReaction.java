package remaster;

import beast.base.core.BEASTObject;
import beast.base.core.Input;
import beast.base.core.Log;
import beast.base.util.Randomizer;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import remaster.parsers.ReactionGrammarBaseListener;
import remaster.parsers.ReactionGrammarLexer;
import remaster.parsers.ReactionGrammarParser;

import java.util.*;

public abstract class AbstractReaction extends BEASTObject {

    public Input<String> reactionStringInput = new Input<>("value",
            "Reaction string.", Input.Validate.REQUIRED);


    Multiset<ReactElement> reactants, products;

    // These fields are used to record the parent-child relationships between
    // reactants and products:
    List<ReactElement> parents;
    List<Multiset<ReactElement>> children;
    List<List<Lineage>> childLineages;

    @Override
    public void initAndValidate() {
        parseReactionString(reactionStringInput.get());
    }

    int currentInterval = 0;

    public void resetInterval() {
        currentInterval = 0;
    }

    public void incrementInterval() {
        currentInterval += 1;
    }

    public abstract double getIntervalEndTime();

    public abstract double[] getAllIntervalEndTimes();

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
        childLineages = new ArrayList<>();

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
                        childLineages.add(new ArrayList<>());

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

                    int parentIndex = 0;
                    if (reactID != null) {
                        if (!parentIDs.containsKey(reactID))
                            throw new IllegalStateException("In reaction '" + reactionString + "' the product ID " +
                                    "'" + reactID + "' is not associated with a reactant.");

                        parentIndex = parentIDs.get(reactID);
                    } else if (parents.stream().anyMatch(p -> p.name.equals(el.name))){
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

        producesSamples = false;
    }


    /**
     * Stoichiometry vector.
     */
    public double[] stoichiometryVector;

    /**
     * Compute stoichiometry vector.  Called on initialisation of
     * trajectory.
     *
     * @param state Trajectory state instance
     */
    public void computeStoichiometry(TrajectoryState state) {
        stoichiometryVector = new double[state.getTotalSubpopCount()];
        for (ReactElement reactElement : reactants)
            stoichiometryVector[state.getOffset(reactElement)] -= 1;

        for (ReactElement reactElement : products) {
            stoichiometryVector[state.getOffset(reactElement)] += 1;
        }
    }

    public boolean isValid(TrajectoryState state) {
        for (ReactElement reactElement : Sets.union(reactants.elementSet(),
                products.elementSet())) {
            if (!state.hasPopNamed(reactElement.name)) {
                Log.err("Reaction " + getID() + " (" + this +
                        ") contains unknown element '" + reactElement.name + "'.");
                return false;
            }
        }

        for (ReactElement reactElement : reactants.elementSet()) {
            if (state.hasSamplePopNamed(reactElement.name)) {
                Log.err("Reaction " + getID() + " (" + this
                        + ") contains sample population '" + reactElement.name
                        + "' as reactant.");
                return false;
            }
        }

        return true;
    }


    /**
     * Increment state according to reaction
     *
     * @param state
     * @param multiplicity
     */
    public void incrementState(TrajectoryState state, double multiplicity) {
        for (ReactElement reactElement : reactants.elementSet())
            state.increment(reactElement.name, reactElement.idx,
                    -multiplicity*reactants.count(reactElement));

        for (ReactElement reactElement : products.elementSet())
            state.increment(reactElement.name, reactElement.idx,
                    multiplicity*products.count(reactElement));
    }

    /**
     * Increment state in reverse according to reaction
     * @param state
     * @param multiplicity
     */
    public void reverseIncremementState(TrajectoryState state, double multiplicity) {
        incrementState(state, -multiplicity);
    }

    /**
     * Hashmap used to track how many of each type of product have already been "seen"
     * when incrementing the lineage state.
     *
     * We make this a field to avoid having to create a new hashmap every time incrementLineages()
     * is called.
     */
    private final Map<ReactElement, Integer> seenElements = new HashMap<>();

    /**
     * List used to keep track of lineages to include in a particular lineage update event.
     *
     * We make this a field to avoid having to create a new arraylist every time incrementLineages()
     * is called.
     */
    private final List<Lineage> toInclude = new ArrayList<>();

    /**
     * Increment lineage state according to reaction.
     *
     * @param lineages current extant lineage population
     * @param state current total population state
     * @param eventTime time of reaction
     * @param lineageFactory factory object for creating new lineages
     * @param conditionOnInclusion increment lineages conditional on at least
     *                             one sampled lineage being involved in the
     *                             reaction.
     */
    public void incrementLineages(Map<ReactElement, List<Lineage>> lineages, TrajectoryState state, double eventTime,
                                  LineageFactory lineageFactory,
                                  boolean conditionOnInclusion) {
        if (lineages.isEmpty() && !producesSamples) {
            if (conditionOnInclusion)
                throw new IllegalStateException("incrementLineages: " +
                        "conditionOnInclusion is true, but no lineages remain.");
            return;
        }

        // Iterate over **products**

        double u = Randomizer.nextDouble();

        double totalInclusionProb = 1.0;
        if (conditionOnInclusion) {
            totalInclusionProb = getLineageInclusionProbability(lineages, state);
            if (totalInclusionProb == 0)
                throw new IllegalStateException("incrementLineages: " +
                        "conditionOnInclusion is true, but totalInclusionProb " +
                        "is zero.");
        }

        for (int i=0; i<children.size(); i++) {
            for (ReactElement el : children.get(i)) {
                if (!seenElements.containsKey(el))
                    seenElements.put(el, 0);

                if (samplePopNames.contains(el.name)) {
                    toInclude.add(lineageFactory.createSample(parents.get(i), eventTime));
                    conditionOnInclusion = false;
                    totalInclusionProb = 1.0;
                    continue;
                }

                double pInclude = lineages.containsKey(el)
                        ? lineages.get(el).size()/(state.get(el) - seenElements.get(el))
                        : 0.0;

                if (u*totalInclusionProb < pInclude) {
                    int lineageIdx = (int)Math.round(Math.floor(lineages.get(el).size()
                            * (u*totalInclusionProb/pInclude)));

                    toInclude.add(lineages.get(el).remove(lineageIdx));

                    u = Randomizer.nextDouble();
                    conditionOnInclusion = false;
                    totalInclusionProb = 1.0;
                } else if (pInclude>0.0) {
                    u = (u*totalInclusionProb - pInclude) / (totalInclusionProb - pInclude);

                    if (conditionOnInclusion)
                        totalInclusionProb = (totalInclusionProb - pInclude)/(1 - pInclude);
                }

                seenElements.put(el, seenElements.get(el)+1);
            }

            if (toInclude.isEmpty())
                continue;

            Lineage parent;
            if (toInclude.size()==1 && toInclude.get(0).reactElement.equals(parents.get(i))) {
                parent = toInclude.get(0);
            } else {
                parent = lineageFactory.createInternal(parents.get(i), eventTime);
                for (Lineage child : toInclude) {
                    parent.addChild(child);
                }
            }

            if (!lineages.containsKey(parent.reactElement))
                lineages.put(parent.reactElement, new ArrayList<>());
            lineages.get(parent.reactElement).add(parent);

            toInclude.clear();
        }

        seenElements.clear();
    }

    /**
     * Compute the probability that a reaction is included in the tree.
     *
     * @param lineages current extant lineage state
     * @param state current total population state
     * @return probability that reaction affects tree
     */
    public double getLineageInclusionProbability(Map<ReactElement, List<Lineage>> lineages,
                                                 TrajectoryState state) {
        if (lineages.isEmpty() && !producesSamples)
            return 0;

        if (producesSamples)
            return 1.0;

        // Iterate over **products**

        double pNoInclude = 1.0;

        for (int i=0; i<children.size(); i++) {
            for (ReactElement el : children.get(i)) {
                if (!seenElements.containsKey(el))
                    seenElements.put(el, 0);

                double pInclude = lineages.containsKey(el)
                        ? lineages.get(el).size()/(state.get(el) - seenElements.get(el))
                        : 0.0;

                pNoInclude *= 1.0 - pInclude;

                seenElements.put(el, seenElements.get(el)+1);
            }
        }

        seenElements.clear();

        return 1.0 - pNoInclude;
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

                sb.append(reactElement.name).append("[")
                        .append(reactElement.idx).append("]");
            }
        }

        return sb.toString();
    }
}
