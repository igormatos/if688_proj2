// Generated from PT_IMSPC.g4 by ANTLR 4.7

  package antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PT_IMSPCParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PT_IMSPCVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PT_IMSPCParser#goal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoal(PT_IMSPCParser.GoalContext ctx);
	/**
	 * Visit a parse tree produced by {@link PT_IMSPCParser#mainClass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainClass(PT_IMSPCParser.MainClassContext ctx);
	/**
	 * Visit a parse tree produced by {@link PT_IMSPCParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(PT_IMSPCParser.ClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PT_IMSPCParser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclaration(PT_IMSPCParser.VarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PT_IMSPCParser#methodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDeclaration(PT_IMSPCParser.MethodDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PT_IMSPCParser#formal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormal(PT_IMSPCParser.FormalContext ctx);
	/**
	 * Visit a parse tree produced by {@link PT_IMSPCParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(PT_IMSPCParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link PT_IMSPCParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(PT_IMSPCParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PT_IMSPCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(PT_IMSPCParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link PT_IMSPCParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(PT_IMSPCParser.IdentifierContext ctx);
}