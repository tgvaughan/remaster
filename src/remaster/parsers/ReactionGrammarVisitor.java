// Generated from /Users/vaughant/code/beast_and_friends/remaster/src/remaster/parsers/ReactionGrammar.g4 by ANTLR 4.9.2
package remaster.parsers;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ReactionGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ReactionGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ReactionGrammarParser#reaction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReaction(ReactionGrammarParser.ReactionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReactionGrammarParser#reactants}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReactants(ReactionGrammarParser.ReactantsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReactionGrammarParser#products}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProducts(ReactionGrammarParser.ProductsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReactionGrammarParser#popsum}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPopsum(ReactionGrammarParser.PopsumContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReactionGrammarParser#popel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPopel(ReactionGrammarParser.PopelContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReactionGrammarParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(ReactionGrammarParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReactionGrammarParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(ReactionGrammarParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReactionGrammarParser#popname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPopname(ReactionGrammarParser.PopnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReactionGrammarParser#loc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoc(ReactionGrammarParser.LocContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReactionGrammarParser#locidx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocidx(ReactionGrammarParser.LocidxContext ctx);
}