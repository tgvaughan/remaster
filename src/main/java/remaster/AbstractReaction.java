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

import beast.base.core.BEASTObject;
import beast.base.core.Input;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import remaster.parsers.ReactionGrammarBaseListener;
import remaster.parsers.ReactionGrammarLexer;
import remaster.parsers.ReactionGrammarParser;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractReaction extends BEASTObject {

    public Input<String> reactionStringInput = new Input<>("value",
            "Reaction string.", Input.Validate.REQUIRED);

    public Multiset<ReactElement> reactants, products;
    public List<ReactElement> reactantList, productList;
    public List<String> reactantIDList, productIDList;

    @Override
    public void initAndValidate() {
        parseReactionString(reactionStringInput.get());
    }

    public int currentInterval = 0;

    public void resetInterval() {
        currentInterval = 0;
    }

    public abstract void resetIntervalToEnd();

    public void incrementInterval() {
        currentInterval += 1;
    }

    public void decrementInterval() {
        currentInterval -= 1;
    }

    public abstract double getIntervalEndTime();

    public abstract double getIntervalStartTime();

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

        reactantList = new ArrayList<>();
        productList = new ArrayList<>();
        reactantIDList = new ArrayList<>();
        productIDList = new ArrayList<>();

        parseTreeWalker.walk(new ReactionGrammarBaseListener() {
            @Override
            public void exitReaction(ReactionGrammarParser.ReactionContext ctx) {

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
                        reactantList.add(el);
                        reactantIDList.add(reactID);
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

                    for (int i=0; i<factor; i++) {
                        productList.add(el);
                        productIDList.add(reactID);
                    }
                }
            }
        }, reactionStringParseTree);
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
