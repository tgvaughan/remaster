// Generated from ConditionGrammar.g4 by ANTLR 4.7.2
package remaster.parsers;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ConditionGrammarParser}.
 */
public interface ConditionGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code Pop}
	 * labeled alternative in {@link ConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPop(ConditionGrammarParser.PopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Pop}
	 * labeled alternative in {@link ConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPop(ConditionGrammarParser.PopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryOp}
	 * labeled alternative in {@link ConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOp(ConditionGrammarParser.UnaryOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryOp}
	 * labeled alternative in {@link ConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOp(ConditionGrammarParser.UnaryOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Number}
	 * labeled alternative in {@link ConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNumber(ConditionGrammarParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Number}
	 * labeled alternative in {@link ConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNumber(ConditionGrammarParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanOp}
	 * labeled alternative in {@link ConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanOp(ConditionGrammarParser.BooleanOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanOp}
	 * labeled alternative in {@link ConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanOp(ConditionGrammarParser.BooleanOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Equality}
	 * labeled alternative in {@link ConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEquality(ConditionGrammarParser.EqualityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Equality}
	 * labeled alternative in {@link ConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEquality(ConditionGrammarParser.EqualityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Bracketed}
	 * labeled alternative in {@link ConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBracketed(ConditionGrammarParser.BracketedContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Bracketed}
	 * labeled alternative in {@link ConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBracketed(ConditionGrammarParser.BracketedContext ctx);
	/**
	 * Enter a parse tree produced by {@link ConditionGrammarParser#popname}.
	 * @param ctx the parse tree
	 */
	void enterPopname(ConditionGrammarParser.PopnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ConditionGrammarParser#popname}.
	 * @param ctx the parse tree
	 */
	void exitPopname(ConditionGrammarParser.PopnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ConditionGrammarParser#loc}.
	 * @param ctx the parse tree
	 */
	void enterLoc(ConditionGrammarParser.LocContext ctx);
	/**
	 * Exit a parse tree produced by {@link ConditionGrammarParser#loc}.
	 * @param ctx the parse tree
	 */
	void exitLoc(ConditionGrammarParser.LocContext ctx);
	/**
	 * Enter a parse tree produced by {@link ConditionGrammarParser#locidx}.
	 * @param ctx the parse tree
	 */
	void enterLocidx(ConditionGrammarParser.LocidxContext ctx);
	/**
	 * Exit a parse tree produced by {@link ConditionGrammarParser#locidx}.
	 * @param ctx the parse tree
	 */
	void exitLocidx(ConditionGrammarParser.LocidxContext ctx);
}