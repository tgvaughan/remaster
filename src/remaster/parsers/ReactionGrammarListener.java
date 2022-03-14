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
	 * Enter a parse tree produced by {@link ReactionGrammarParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(ReactionGrammarParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReactionGrammarParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(ReactionGrammarParser.AssignmentContext ctx);
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
	/**
	 * Enter a parse tree produced by the {@code UnaryOp}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOp(ReactionGrammarParser.UnaryOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryOp}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOp(ReactionGrammarParser.UnaryOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterVariable(ReactionGrammarParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitVariable(ReactionGrammarParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Negation}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNegation(ReactionGrammarParser.NegationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Negation}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNegation(ReactionGrammarParser.NegationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMulDiv(ReactionGrammarParser.MulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMulDiv(ReactionGrammarParser.MulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(ReactionGrammarParser.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(ReactionGrammarParser.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanOp}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanOp(ReactionGrammarParser.BooleanOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanOp}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanOp(ReactionGrammarParser.BooleanOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Exponentiation}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExponentiation(ReactionGrammarParser.ExponentiationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Exponentiation}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExponentiation(ReactionGrammarParser.ExponentiationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Bracketed}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBracketed(ReactionGrammarParser.BracketedContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Bracketed}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBracketed(ReactionGrammarParser.BracketedContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Array}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArray(ReactionGrammarParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Array}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArray(ReactionGrammarParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Factorial}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFactorial(ReactionGrammarParser.FactorialContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Factorial}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFactorial(ReactionGrammarParser.FactorialContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Function}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFunction(ReactionGrammarParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Function}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFunction(ReactionGrammarParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Number}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNumber(ReactionGrammarParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Number}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNumber(ReactionGrammarParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArraySubscript}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArraySubscript(ReactionGrammarParser.ArraySubscriptContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArraySubscript}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArraySubscript(ReactionGrammarParser.ArraySubscriptContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Equality}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEquality(ReactionGrammarParser.EqualityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Equality}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEquality(ReactionGrammarParser.EqualityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfThenElse}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIfThenElse(ReactionGrammarParser.IfThenElseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfThenElse}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIfThenElse(ReactionGrammarParser.IfThenElseContext ctx);
}