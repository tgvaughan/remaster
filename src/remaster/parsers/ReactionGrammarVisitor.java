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
	 * Visit a parse tree produced by {@link ReactionGrammarParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(ReactionGrammarParser.AssignmentContext ctx);
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
	/**
	 * Visit a parse tree produced by the {@code UnaryOp}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOp(ReactionGrammarParser.UnaryOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(ReactionGrammarParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Negation}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegation(ReactionGrammarParser.NegationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDiv(ReactionGrammarParser.MulDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSub(ReactionGrammarParser.AddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanOp}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanOp(ReactionGrammarParser.BooleanOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Exponentiation}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExponentiation(ReactionGrammarParser.ExponentiationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Bracketed}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBracketed(ReactionGrammarParser.BracketedContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Array}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(ReactionGrammarParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Factorial}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactorial(ReactionGrammarParser.FactorialContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Function}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(ReactionGrammarParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Number}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(ReactionGrammarParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArraySubscript}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArraySubscript(ReactionGrammarParser.ArraySubscriptContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Equality}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquality(ReactionGrammarParser.EqualityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfThenElse}
	 * labeled alternative in {@link ReactionGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfThenElse(ReactionGrammarParser.IfThenElseContext ctx);
}