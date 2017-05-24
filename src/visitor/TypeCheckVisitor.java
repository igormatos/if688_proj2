package visitor;

import symboltable.SymbolTable;
import ast.And;
import ast.ArrayAssign;
import ast.ArrayLength;
import ast.ArrayLookup;
import ast.Assign;
import ast.Block;
import ast.BooleanType;
import ast.Call;
import ast.ClassDeclExtends;
import ast.ClassDeclSimple;
import ast.False;
import ast.Formal;
import ast.Identifier;
import ast.IdentifierExp;
import ast.IdentifierType;
import ast.If;
import ast.IntArrayType;
import ast.IntegerLiteral;
import ast.IntegerType;
import ast.LessThan;
import ast.MainClass;
import ast.MethodDecl;
import ast.Minus;
import ast.NewArray;
import ast.NewObject;
import ast.Not;
import ast.Plus;
import ast.Print;
import ast.Program;
import ast.This;
import ast.Times;
import ast.True;
import ast.Type;
import ast.VarDecl;
import ast.While;

public class TypeCheckVisitor implements TypeVisitor {

	private SymbolTable symbolTable;

	public TypeCheckVisitor(SymbolTable st) {
		symbolTable = st;
	}

	// LINHAS COMENTADAS NAO PRECISAM DE VERIFICACAO DE TIPOS OU SAO FOLHAS ONDE TIPO NAO INTERESSA
	// RETORNA NULL QUANDO ELEMENTO VISITADO NÃO CONTIVER TIPO


	protected  void verifyMatch(Type a, Type b) throws Exception{
		boolean equals = a.getName() == b.getName();
		log("verifyMatch: " + a.getName() + ", " + b.getName());
		if(!equals)
			throw new Exception("Classes incompatíveis: " + b.getName() + " e " + a.getName());
	}
	protected void verifyBool(Type a) throws Exception{
		log("verifyBool: " + a.getName() + " == " + (a instanceof BooleanType));
		if(!(a instanceof BooleanType))
			throw new Exception("Esperado boolean onde foi encontrado " + a.getName());
	}






	// MainClass m;
	// ClassDeclList cl;
	public Type visit(Program n)  throws Exception{

		n.m.accept(this);
		for (int i = 0; i < n.cl.size(); i++) {
			n.cl.elementAt(i).accept(this);
		}
		return null;
	}

	// Identifier i1,i2;
	// Statement s;
	public Type visit(MainClass n) throws Exception{
		// n.i1.accept(this);
		// n.i2.accept(this);
		n.s.accept(this);
		return null;
	}

	// Identifier i;
	// VarDeclList vl;
	// MethodDeclList ml;
	public Type visit(ClassDeclSimple n) throws Exception{
		// n.i.accept(this);

		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.ml.size(); i++) {
			n.ml.elementAt(i).accept(this);
		}
		return null;
	}

	// Identifier i;
	// Identifier j;
	// VarDeclList vl;
	// MethodDeclList ml;
	public Type visit(ClassDeclExtends n) throws Exception{
		n.i.accept(this);
		n.j.accept(this);
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.ml.size(); i++) {
			n.ml.elementAt(i).accept(this);
		}
		return null;
	}

	// Type t;
	// Identifier i;
	public Type visit(VarDecl n)  throws Exception{
		// n.t.accept(this);
		// n.i.accept(this);
		return null;
	}

	// Type t;
	// Identifier i;
	// FormalList fl;
	// VarDeclList vl;
	// StatementList sl;
	// Exp e;
	public Type visit(MethodDecl n) throws Exception {

		Type methType = n.t.accept(this);
		Type methRetT = n.e.accept(this);


		// n.i.accept(this);
		for (int i = 0; i < n.fl.size(); i++) {
			n.fl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.sl.size(); i++) {
			n.sl.elementAt(i).accept(this);
		}

		verifyMatch(methType, methRetT);

		return null;
	}

	// Type t;
	// Identifier i;
	public Type visit(Formal n)  throws Exception{
		n.t.accept(this);
		n.i.accept(this);
		return null;
	}

	public Type visit(IntArrayType n) throws Exception {
		return n;
	}

	public Type visit(BooleanType n) throws Exception {
		return n;
	}

	public Type visit(IntegerType n)  throws Exception{
		return n;
	}

	// String s;
	public Type visit(IdentifierType n)  throws Exception{
		return n;
	}

	// StatementList sl;
	public Type visit(Block n) throws Exception{
		for (int i = 0; i < n.sl.size(); i++) {
			n.sl.elementAt(i).accept(this);
		}
		return null;
	}

	// Exp e;
	// Statement s1,s2;
	public Type visit(If n)  throws Exception{
		Type condition = n.e.accept(this);

		verifyBool(condition);

		n.s1.accept(this);
		n.s2.accept(this);
		return null;
	}

	// Exp e;
	// Statement s;
	public Type visit(While n) throws Exception{
		n.e.accept(this);
		n.s.accept(this);
		return null;
	}

	// Exp e;
	public Type visit(Print n)  throws Exception{
		n.e.accept(this);
		return null;
	}

	// Identifier i;
	// Exp e;
	public Type visit(Assign n)  throws Exception{
		n.i.accept(this);
		n.e.accept(this);
		return null;
	}

	// Identifier i;
	// Exp e1,e2;
	public Type visit(ArrayAssign n)  throws Exception{
		n.i.accept(this);
		n.e1.accept(this);
		n.e2.accept(this);
		return null;
	}

	// Exp e1,e2;
	public Type visit(And n) throws Exception {
		n.e1.accept(this);
		n.e2.accept(this);
		return null;
	}

	// Exp e1,e2;
	public Type visit(LessThan n) throws Exception {
		n.e1.accept(this);
		n.e2.accept(this);
		return null;
	}

	// Exp e1,e2;
	public Type visit(Plus n)  throws Exception{
		n.e1.accept(this);
		n.e2.accept(this);
		return null;
	}

	// Exp e1,e2;
	public Type visit(Minus n) throws Exception {
		n.e1.accept(this);
		n.e2.accept(this);
		return null;
	}

	// Exp e1,e2;
	public Type visit(Times n)  throws Exception{
		n.e1.accept(this);
		n.e2.accept(this);
		return null;
	}

	// Exp e1,e2;
	public Type visit(ArrayLookup n) throws Exception {
		n.e1.accept(this);
		n.e2.accept(this);
		return null;
	}

	// Exp e;
	public Type visit(ArrayLength n)  throws Exception{
		n.e.accept(this);
		return null;
	}

	// Exp e;
	// Identifier i;
	// ExpList el;
	public Type visit(Call n)  throws Exception{
		n.e.accept(this);
		n.i.accept(this);
		for (int i = 0; i < n.el.size(); i++) {
			n.el.elementAt(i).accept(this);
		}
		return null;
	}

	// int i;
	public Type visit(IntegerLiteral n)  throws Exception{
		return null;
	}

	public Type visit(True n)  throws Exception{
		return null;
	}

	public Type visit(False n)  throws Exception{
		return null;
	}

	// String s;
	public Type visit(IdentifierExp n)  throws Exception{
		return null;
	}

	public Type visit(This n)  throws Exception{
		return null;
	}

	// Exp e;
	public Type visit(NewArray n) throws Exception {
		n.e.accept(this);
		return null;
	}

	// Identifier i;
	public Type visit(NewObject n) throws Exception {
		return null;
	}

	// Exp e;
	public Type visit(Not n) throws Exception {
		n.e.accept(this);
		return null;
	}

	// String s;
	public Type visit(Identifier n)  throws Exception{
		return null;
	}

	protected void log(String msg){
		System.out.println(
			"> " + msg
		);
	}
}
