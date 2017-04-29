// Generated from PT_IMSPC.g4 by ANTLR 4.7

  package antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PT_IMSPCParser}.
 */
public interface PT_IMSPCListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PT_IMSPCParser#goal}.
	 * @param ctx the parse tree
	 */
	void enterGoal(PT_IMSPCParser.GoalContext ctx);
	/**
	 * Exit a parse tree produced by {@link PT_IMSPCParser#goal}.
	 * @param ctx the parse tree
	 */
	void exitGoal(PT_IMSPCParser.GoalContext ctx);
	/**
	 * Enter a parse tree produced by {@link PT_IMSPCParser#mainClass}.
	 * @param ctx the parse tree
	 */
	void enterMainClass(PT_IMSPCParser.MainClassContext ctx);
	/**
	 * Exit a parse tree produced by {@link PT_IMSPCParser#mainClass}.
	 * @param ctx the parse tree
	 */
	void exitMainClass(PT_IMSPCParser.MainClassContext ctx);
	/**
	 * Enter a parse tree produced by {@link PT_IMSPCParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(PT_IMSPCParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PT_IMSPCParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(PT_IMSPCParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PT_IMSPCParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(PT_IMSPCParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PT_IMSPCParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(PT_IMSPCParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PT_IMSPCParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(PT_IMSPCParser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PT_IMSPCParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(PT_IMSPCParser.MethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PT_IMSPCParser#formal}.
	 * @param ctx the parse tree
	 */
	void enterFormal(PT_IMSPCParser.FormalContext ctx);
	/**
	 * Exit a parse tree produced by {@link PT_IMSPCParser#formal}.
	 * @param ctx the parse tree
	 */
	void exitFormal(PT_IMSPCParser.FormalContext ctx);
	/**
	 * Enter a parse tree produced by {@link PT_IMSPCParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(PT_IMSPCParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PT_IMSPCParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(PT_IMSPCParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PT_IMSPCParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(PT_IMSPCParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PT_IMSPCParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(PT_IMSPCParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PT_IMSPCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(PT_IMSPCParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PT_IMSPCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(PT_IMSPCParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PT_IMSPCParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(PT_IMSPCParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link PT_IMSPCParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(PT_IMSPCParser.IdentifierContext ctx);
}