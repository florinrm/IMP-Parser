// Generated from Hello.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HelloParser}.
 */
public interface HelloListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HelloParser#main}.
	 * @param ctx the parse tree
	 */
	void enterMain(HelloParser.MainContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#main}.
	 * @param ctx the parse tree
	 */
	void exitMain(HelloParser.MainContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#variablesList}.
	 * @param ctx the parse tree
	 */
	void enterVariablesList(HelloParser.VariablesListContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#variablesList}.
	 * @param ctx the parse tree
	 */
	void exitVariablesList(HelloParser.VariablesListContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void enterIf_stmt(HelloParser.If_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void exitIf_stmt(HelloParser.If_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#while_stmt}.
	 * @param ctx the parse tree
	 */
	void enterWhile_stmt(HelloParser.While_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#while_stmt}.
	 * @param ctx the parse tree
	 */
	void exitWhile_stmt(HelloParser.While_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#a_expression}.
	 * @param ctx the parse tree
	 */
	void enterA_expression(HelloParser.A_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#a_expression}.
	 * @param ctx the parse tree
	 */
	void exitA_expression(HelloParser.A_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#b_expression}.
	 * @param ctx the parse tree
	 */
	void enterB_expression(HelloParser.B_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#b_expression}.
	 * @param ctx the parse tree
	 */
	void exitB_expression(HelloParser.B_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#sequence}.
	 * @param ctx the parse tree
	 */
	void enterSequence(HelloParser.SequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#sequence}.
	 * @param ctx the parse tree
	 */
	void exitSequence(HelloParser.SequenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(HelloParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(HelloParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(HelloParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(HelloParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#integer}.
	 * @param ctx the parse tree
	 */
	void enterInteger(HelloParser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#integer}.
	 * @param ctx the parse tree
	 */
	void exitInteger(HelloParser.IntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#plus}.
	 * @param ctx the parse tree
	 */
	void enterPlus(HelloParser.PlusContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#plus}.
	 * @param ctx the parse tree
	 */
	void exitPlus(HelloParser.PlusContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(HelloParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(HelloParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#div}.
	 * @param ctx the parse tree
	 */
	void enterDiv(HelloParser.DivContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#div}.
	 * @param ctx the parse tree
	 */
	void exitDiv(HelloParser.DivContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#not}.
	 * @param ctx the parse tree
	 */
	void enterNot(HelloParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#not}.
	 * @param ctx the parse tree
	 */
	void exitNot(HelloParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#greater}.
	 * @param ctx the parse tree
	 */
	void enterGreater(HelloParser.GreaterContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#greater}.
	 * @param ctx the parse tree
	 */
	void exitGreater(HelloParser.GreaterContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#and}.
	 * @param ctx the parse tree
	 */
	void enterAnd(HelloParser.AndContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#and}.
	 * @param ctx the parse tree
	 */
	void exitAnd(HelloParser.AndContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#bool}.
	 * @param ctx the parse tree
	 */
	void enterBool(HelloParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#bool}.
	 * @param ctx the parse tree
	 */
	void exitBool(HelloParser.BoolContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#bracket}.
	 * @param ctx the parse tree
	 */
	void enterBracket(HelloParser.BracketContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#bracket}.
	 * @param ctx the parse tree
	 */
	void exitBracket(HelloParser.BracketContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(HelloParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(HelloParser.BlockContext ctx);
}