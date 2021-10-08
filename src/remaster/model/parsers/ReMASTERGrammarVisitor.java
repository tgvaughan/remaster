// Generated from /Users/vaughant/code/beast_and_friends/reMASTER/src/remaster/model/parsers/ReMASTERGrammar.g4 by ANTLR 4.9.1
package remaster.model.parsers;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ReMASTERGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ReMASTERGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ReMASTERGrammarParser#reaction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReaction(ReMASTERGrammarParser.ReactionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReMASTERGrammarParser#reactants}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReactants(ReMASTERGrammarParser.ReactantsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReMASTERGrammarParser#products}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProducts(ReMASTERGrammarParser.ProductsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReMASTERGrammarParser#popsum}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPopsum(ReMASTERGrammarParser.PopsumContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReMASTERGrammarParser#popel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPopel(ReMASTERGrammarParser.PopelContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReMASTERGrammarParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(ReMASTERGrammarParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReMASTERGrammarParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(ReMASTERGrammarParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReMASTERGrammarParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(ReMASTERGrammarParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReMASTERGrammarParser#popname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPopname(ReMASTERGrammarParser.PopnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReMASTERGrammarParser#loc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoc(ReMASTERGrammarParser.LocContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReMASTERGrammarParser#locel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocel(ReMASTERGrammarParser.LocelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryOp}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOp(ReMASTERGrammarParser.UnaryOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(ReMASTERGrammarParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Negation}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegation(ReMASTERGrammarParser.NegationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDiv(ReMASTERGrammarParser.MulDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSub(ReMASTERGrammarParser.AddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanOp}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanOp(ReMASTERGrammarParser.BooleanOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Exponentiation}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExponentiation(ReMASTERGrammarParser.ExponentiationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Bracketed}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBracketed(ReMASTERGrammarParser.BracketedContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Array}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(ReMASTERGrammarParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Factorial}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactorial(ReMASTERGrammarParser.FactorialContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Function}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(ReMASTERGrammarParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Number}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(ReMASTERGrammarParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArraySubscript}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArraySubscript(ReMASTERGrammarParser.ArraySubscriptContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Equality}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquality(ReMASTERGrammarParser.EqualityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfThenElse}
	 * labeled alternative in {@link ReMASTERGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfThenElse(ReMASTERGrammarParser.IfThenElseContext ctx);
}