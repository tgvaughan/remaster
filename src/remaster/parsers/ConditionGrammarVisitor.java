// Generated from ConditionGrammar.g4 by ANTLR 4.7.2
package remaster.parsers;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ConditionGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ConditionGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code Pop}
	 * labeled alternative in {@link ConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPop(ConditionGrammarParser.PopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryOp}
	 * labeled alternative in {@link ConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOp(ConditionGrammarParser.UnaryOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Number}
	 * labeled alternative in {@link ConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(ConditionGrammarParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link ConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDiv(ConditionGrammarParser.MulDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link ConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSub(ConditionGrammarParser.AddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanOp}
	 * labeled alternative in {@link ConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanOp(ConditionGrammarParser.BooleanOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Equality}
	 * labeled alternative in {@link ConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquality(ConditionGrammarParser.EqualityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Bracketed}
	 * labeled alternative in {@link ConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBracketed(ConditionGrammarParser.BracketedContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConditionGrammarParser#popname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPopname(ConditionGrammarParser.PopnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConditionGrammarParser#loc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoc(ConditionGrammarParser.LocContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConditionGrammarParser#locidx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocidx(ConditionGrammarParser.LocidxContext ctx);
}