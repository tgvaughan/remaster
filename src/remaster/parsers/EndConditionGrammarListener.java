// Generated from EndConditionGrammar.g4 by ANTLR 4.7.2
package remaster.parsers;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link EndConditionGrammarParser}.
 */
public interface EndConditionGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code Pop}
	 * labeled alternative in {@link EndConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPop(EndConditionGrammarParser.PopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Pop}
	 * labeled alternative in {@link EndConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPop(EndConditionGrammarParser.PopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryOp}
	 * labeled alternative in {@link EndConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOp(EndConditionGrammarParser.UnaryOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryOp}
	 * labeled alternative in {@link EndConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOp(EndConditionGrammarParser.UnaryOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Number}
	 * labeled alternative in {@link EndConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNumber(EndConditionGrammarParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Number}
	 * labeled alternative in {@link EndConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNumber(EndConditionGrammarParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanOp}
	 * labeled alternative in {@link EndConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanOp(EndConditionGrammarParser.BooleanOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanOp}
	 * labeled alternative in {@link EndConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanOp(EndConditionGrammarParser.BooleanOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Equality}
	 * labeled alternative in {@link EndConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEquality(EndConditionGrammarParser.EqualityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Equality}
	 * labeled alternative in {@link EndConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEquality(EndConditionGrammarParser.EqualityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Bracketed}
	 * labeled alternative in {@link EndConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBracketed(EndConditionGrammarParser.BracketedContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Bracketed}
	 * labeled alternative in {@link EndConditionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBracketed(EndConditionGrammarParser.BracketedContext ctx);
	/**
	 * Enter a parse tree produced by {@link EndConditionGrammarParser#popname}.
	 * @param ctx the parse tree
	 */
	void enterPopname(EndConditionGrammarParser.PopnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link EndConditionGrammarParser#popname}.
	 * @param ctx the parse tree
	 */
	void exitPopname(EndConditionGrammarParser.PopnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link EndConditionGrammarParser#loc}.
	 * @param ctx the parse tree
	 */
	void enterLoc(EndConditionGrammarParser.LocContext ctx);
	/**
	 * Exit a parse tree produced by {@link EndConditionGrammarParser#loc}.
	 * @param ctx the parse tree
	 */
	void exitLoc(EndConditionGrammarParser.LocContext ctx);
	/**
	 * Enter a parse tree produced by {@link EndConditionGrammarParser#locidx}.
	 * @param ctx the parse tree
	 */
	void enterLocidx(EndConditionGrammarParser.LocidxContext ctx);
	/**
	 * Exit a parse tree produced by {@link EndConditionGrammarParser#locidx}.
	 * @param ctx the parse tree
	 */
	void exitLocidx(EndConditionGrammarParser.LocidxContext ctx);
}