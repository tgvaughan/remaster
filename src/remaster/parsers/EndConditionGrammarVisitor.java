// Generated from EndConditionGrammar.g4 by ANTLR 4.7.2
package remaster.parsers;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link EndConditionGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface EndConditionGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code Pop}
	 * labeled alternative in {@link EndConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPop(EndConditionGrammarParser.PopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryOp}
	 * labeled alternative in {@link EndConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOp(EndConditionGrammarParser.UnaryOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Number}
	 * labeled alternative in {@link EndConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(EndConditionGrammarParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanOp}
	 * labeled alternative in {@link EndConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanOp(EndConditionGrammarParser.BooleanOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Equality}
	 * labeled alternative in {@link EndConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquality(EndConditionGrammarParser.EqualityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Bracketed}
	 * labeled alternative in {@link EndConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBracketed(EndConditionGrammarParser.BracketedContext ctx);
	/**
	 * Visit a parse tree produced by {@link EndConditionGrammarParser#popname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPopname(EndConditionGrammarParser.PopnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link EndConditionGrammarParser#loc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoc(EndConditionGrammarParser.LocContext ctx);
	/**
	 * Visit a parse tree produced by {@link EndConditionGrammarParser#locidx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocidx(EndConditionGrammarParser.LocidxContext ctx);
}