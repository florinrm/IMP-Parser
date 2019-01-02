// Generated from Hello.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HelloParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HelloVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HelloParser#main}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMain(HelloParser.MainContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#variablesList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariablesList(HelloParser.VariablesListContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#if_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_stmt(HelloParser.If_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#while_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_stmt(HelloParser.While_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#a_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitA_expression(HelloParser.A_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#b_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitB_expression(HelloParser.B_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#sequence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSequence(HelloParser.SequenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(HelloParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(HelloParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#integer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger(HelloParser.IntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#plus}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlus(HelloParser.PlusContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(HelloParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#div}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiv(HelloParser.DivContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#not}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(HelloParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#greater}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreater(HelloParser.GreaterContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#and}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(HelloParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(HelloParser.BoolContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#bracket}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBracket(HelloParser.BracketContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(HelloParser.BlockContext ctx);
}