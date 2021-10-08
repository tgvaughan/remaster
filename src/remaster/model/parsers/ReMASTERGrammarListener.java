// Generated from /Users/vaughant/code/beast_and_friends/reMASTER/src/remaster/model/parsers/ReMASTERGrammar.g4 by ANTLR 4.9.1
package remaster.model.parsers;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ReMASTERGrammarParser}.
 */
public interface ReMASTERGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ReMASTERGrammarParser#reaction}.
	 * @param ctx the parse tree
	 */
	void enterReaction(ReMASTERGrammarParser.ReactionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReMASTERGrammarParser#reaction}.
	 * @param ctx the parse tree
	 */
	void exitReaction(ReMASTERGrammarParser.ReactionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReMASTERGrammarParser#reactants}.
	 * @param ctx the parse tree
	 */
	void enterReactants(ReMASTERGrammarParser.ReactantsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReMASTERGrammarParser#reactants}.
	 * @param ctx the parse tree
	 */
	void exitReactants(ReMASTERGrammarParser.ReactantsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReMASTERGrammarParser#products}.
	 * @param ctx the parse tree
	 */
	void enterProducts(ReMASTERGrammarParser.ProductsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReMASTERGrammarParser#products}.
	 * @param ctx the parse tree
	 */
	void exitProducts(ReMASTERGrammarParser.ProductsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReMASTERGrammarParser#popsum}.
	 * @param ctx the parse tree
	 */
	void enterPopsum(ReMASTERGrammarParser.PopsumContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReMASTERGrammarParser#popsum}.
	 * @param ctx the parse tree
	 */
	void exitPopsum(ReMASTERGrammarParser.PopsumContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReMASTERGrammarParser#popel}.
	 * @param ctx the parse tree
	 */
	void enterPopel(ReMASTERGrammarParser.PopelContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReMASTERGrammarParser#popel}.
	 * @param ctx the parse tree
	 */
	void exitPopel(ReMASTERGrammarParser.PopelContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReMASTERGrammarParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(ReMASTERGrammarParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReMASTERGrammarParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(ReMASTERGrammarParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReMASTERGrammarParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(ReMASTERGrammarParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReMASTERGrammarParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(ReMASTERGrammarParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReMASTERGrammarParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(ReMASTERGrammarParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReMASTERGrammarParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(ReMASTERGrammarParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReMASTERGrammarParser#popname}.
	 * @param ctx the parse tree
	 */
	void enterPopname(ReMASTERGrammarParser.PopnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReMASTERGrammarParser#popname}.
	 * @param ctx the parse tree
	 */
	void exitPopname(ReMASTERGrammarParser.PopnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReMASTERGrammarParser#loc}.
	 * @param ctx the parse tree
	 */
	void enterLoc(ReMASTERGrammarParser.LocContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReMASTERGrammarParser#loc}.
	 * @param ctx the parse tree
	 */
	void exitLoc(ReMASTERGrammarParser.LocContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReMASTERGrammarParser#locel}.
	 * @param ctx the parse tree
	 */
	void enterLocel(ReMASTERGrammarParser.LocelContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReMASTERGrammarParser#locel}.
	 * @param ctx the parse tree
	 */
	void exitLocel(ReMASTERGrammarParser.LocelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryOp}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOp(ReMASTERGrammarParser.UnaryOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryOp}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOp(ReMASTERGrammarParser.UnaryOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterVariable(ReMASTERGrammarParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitVariable(ReMASTERGrammarParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Negation}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNegation(ReMASTERGrammarParser.NegationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Negation}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNegation(ReMASTERGrammarParser.NegationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMulDiv(ReMASTERGrammarParser.MulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMulDiv(ReMASTERGrammarParser.MulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(ReMASTERGrammarParser.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(ReMASTERGrammarParser.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanOp}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanOp(ReMASTERGrammarParser.BooleanOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanOp}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanOp(ReMASTERGrammarParser.BooleanOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Exponentiation}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExponentiation(ReMASTERGrammarParser.ExponentiationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Exponentiation}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExponentiation(ReMASTERGrammarParser.ExponentiationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Bracketed}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBracketed(ReMASTERGrammarParser.BracketedContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Bracketed}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBracketed(ReMASTERGrammarParser.BracketedContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Array}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArray(ReMASTERGrammarParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Array}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArray(ReMASTERGrammarParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Factorial}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFactorial(ReMASTERGrammarParser.FactorialContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Factorial}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFactorial(ReMASTERGrammarParser.FactorialContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Function}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFunction(ReMASTERGrammarParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Function}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFunction(ReMASTERGrammarParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Number}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNumber(ReMASTERGrammarParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Number}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNumber(ReMASTERGrammarParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArraySubscript}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArraySubscript(ReMASTERGrammarParser.ArraySubscriptContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArraySubscript}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArraySubscript(ReMASTERGrammarParser.ArraySubscriptContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Equality}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEquality(ReMASTERGrammarParser.EqualityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Equality}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEquality(ReMASTERGrammarParser.EqualityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfThenElse}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIfThenElse(ReMASTERGrammarParser.IfThenElseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfThenElse}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIfThenElse(ReMASTERGrammarParser.IfThenElseContext ctx);
}