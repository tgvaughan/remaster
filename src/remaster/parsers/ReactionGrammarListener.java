// Generated from /Users/vaughant/code/beast_and_friends/remaster/src/remaster/parsers/ReactionGrammar.g4 by ANTLR 4.9.2
package remaster.parsers;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ReactionGrammarParser}.
 */
public interface ReactionGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ReactionGrammarParser#reaction}.
	 * @param ctx the parse tree
	 */
	void enterReaction(ReactionGrammarParser.ReactionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReactionGrammarParser#reaction}.
	 * @param ctx the parse tree
	 */
	void exitReaction(ReactionGrammarParser.ReactionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReactionGrammarParser#reactants}.
	 * @param ctx the parse tree
	 */
	void enterReactants(ReactionGrammarParser.ReactantsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReactionGrammarParser#reactants}.
	 * @param ctx the parse tree
	 */
	void exitReactants(ReactionGrammarParser.ReactantsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReactionGrammarParser#products}.
	 * @param ctx the parse tree
	 */
	void enterProducts(ReactionGrammarParser.ProductsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReactionGrammarParser#products}.
	 * @param ctx the parse tree
	 */
	void exitProducts(ReactionGrammarParser.ProductsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReactionGrammarParser#popsum}.
	 * @param ctx the parse tree
	 */
	void enterPopsum(ReactionGrammarParser.PopsumContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReactionGrammarParser#popsum}.
	 * @param ctx the parse tree
	 */
	void exitPopsum(ReactionGrammarParser.PopsumContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReactionGrammarParser#popel}.
	 * @param ctx the parse tree
	 */
	void enterPopel(ReactionGrammarParser.PopelContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReactionGrammarParser#popel}.
	 * @param ctx the parse tree
	 */
	void exitPopel(ReactionGrammarParser.PopelContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReactionGrammarParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(ReactionGrammarParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReactionGrammarParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(ReactionGrammarParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReactionGrammarParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(ReactionGrammarParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReactionGrammarParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(ReactionGrammarParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReactionGrammarParser#popname}.
	 * @param ctx the parse tree
	 */
	void enterPopname(ReactionGrammarParser.PopnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReactionGrammarParser#popname}.
	 * @param ctx the parse tree
	 */
	void exitPopname(ReactionGrammarParser.PopnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReactionGrammarParser#loc}.
	 * @param ctx the parse tree
	 */
	void enterLoc(ReactionGrammarParser.LocContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReactionGrammarParser#loc}.
	 * @param ctx the parse tree
	 */
	void exitLoc(ReactionGrammarParser.LocContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReactionGrammarParser#locidx}.
	 * @param ctx the parse tree
	 */
	void enterLocidx(ReactionGrammarParser.LocidxContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReactionGrammarParser#locidx}.
	 * @param ctx the parse tree
	 */
	void exitLocidx(ReactionGrammarParser.LocidxContext ctx);
}