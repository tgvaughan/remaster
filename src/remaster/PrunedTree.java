/*
 * Copyright (c) 2024 Tim Vaughan
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

import beast.base.core.Input;
import beast.base.evolution.tree.Node;
import beast.base.evolution.tree.Tree;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import remaster.parsers.ReactionGrammarBaseListener;
import remaster.parsers.ReactionGrammarLexer;
import remaster.parsers.ReactionGrammarParser;

import java.util.ArrayList;
import java.util.List;

public class PrunedTree extends Tree {

    public Input<String> samplePopsStringInput = new Input<>("samplePops",
            "Comma-delimited list of population elements to include",
            Input.Validate.REQUIRED);

    public Input<SimulatedTree> simulatedTreeInput = new Input<>("simulatedTree",
            "Simulated tree to prune.",
            Input.Validate.REQUIRED);

    @Override
    public void initAndValidate() {
        List<ReactElement> samplePops = parseSamplePopsString(samplePopsStringInput.get());

        nextNodeNr = 0;
        Node root = pruneTree(simulatedTreeInput.get().getRoot(), samplePops);
        numberInternals(root);

        assignFromWithoutID(new Tree(root));
    }

    int nextNodeNr;
    protected Node pruneTree(Node node, List<ReactElement> samplePops) {

        List<Node> prunedChildren = new ArrayList<>();
        Node prunedNode;

        for (Node child : node.getChildren()) {
            Node prunedChild = pruneTree(child, samplePops);
            if (prunedChild != null)
                prunedChildren.add(prunedChild);
        }

        if (prunedChildren.isEmpty()) {
            if (!node.getMetaDataNames().contains("samp")) {
                return null;
            }

            ReactElement sampEl = (ReactElement) node.getMetaData("samp");
            if (!samplePops.contains(sampEl)) {
                return null;
            }
        }

        // Skip singleton nodes which don't change type
        if (prunedChildren.size()==1) {
            if (prunedChildren.get(0).getMetaData("type").equals(node.getMetaData("type")))
                return prunedChildren.get(0);
        }

        prunedNode = new Node();
        if (prunedChildren.isEmpty()) {
            prunedNode.setNr(nextNodeNr);
            prunedNode.setID("leaf_" + String.valueOf(nextNodeNr));
            nextNodeNr += 1;
            prunedNode.setMetaData("samp", node.getMetaData("samp"));
        } else {
            for (Node prunedChild : prunedChildren)
                prunedNode.addChild(prunedChild);
        }
        prunedNode.setMetaData("type", node.getMetaData("type"));
        prunedNode.metaDataString = node.metaDataString;
        prunedNode.setHeight(node.getHeight());

        return prunedNode;
    }

    protected void numberInternals(Node root) {
        if (!root.isLeaf()) {
            for (Node child : root.getChildren())
                numberInternals(child);

            root.setNr(nextNodeNr);

            nextNodeNr += 1;
        }
    }

    protected List<ReactElement> parseSamplePopsString(String samplePopsString) {
        CharStream rsInput = CharStreams.fromString(samplePopsString);

        // Custom parser/lexer error listener
        BaseErrorListener errorListener = new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer,
                                    Object offendingSymbol,
                                    int line, int charPositionInLine,
                                    String msg, RecognitionException e) {
                throw new RuntimeException("Error parsing character " +
                        charPositionInLine + " of reaction " +
                        "string '" + samplePopsString + "': " + msg);
            }
        };

        ReactionGrammarLexer rsLexer = new ReactionGrammarLexer(rsInput);
        rsLexer.removeErrorListeners();
        rsLexer.addErrorListener(errorListener);

        CommonTokenStream rsTokens = new CommonTokenStream(rsLexer);
        ReactionGrammarParser rsParser = new ReactionGrammarParser(rsTokens);
        rsParser.removeErrorListeners();
        rsParser.addErrorListener(errorListener);
        ParseTree reactListParseTree = rsParser.reactlist();
        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();

        List<ReactElement> samplePops = new ArrayList<>();

        parseTreeWalker.walk(new ReactionGrammarBaseListener() {
            @Override
            public void exitReactlist(ReactionGrammarParser.ReactlistContext ctx) {
                for (ReactionGrammarParser.SinglepopelContext singlepopelCtx : ctx.singlepopel()) {
                    String name = singlepopelCtx.popname().getText();
                    int idx = singlepopelCtx.loc() == null ? 0
                            : Integer.parseInt(singlepopelCtx.loc().locidx().getText());
                    samplePops.add(new ReactElement(name, idx));
                }
            }
        }, reactListParseTree);

        return samplePops;
    }
}
