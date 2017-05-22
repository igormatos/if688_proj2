import antlr.*;
import ast.*;
import visitor.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.nio.file.Files;



public class PT_IMSPC{

  static String testingPath = "../testing";
  static String srcFilePath = "";

  public static void main(String[] args){
    try{


      processFile(
        getSrcFile(
          getSrcFileArg(args)
        )
      );

    }catch(Exception e){
      System.out.println(e.getMessage());
    }
  }
  public static String getSrcFileArg(String[] args) throws Exception{
    if(args.length < 1)
      throw new Exception("Argumento faltando: Arquivo de entrada.");

    return args[0];
  }
  public static File getSrcFile(String arg) throws Exception{

      srcFilePath = testingPath + "/" + arg + ".java";
      File srcFile = new File(srcFilePath);

    if(! srcFile.exists())
      throw new Exception("Arquivo " + srcFilePath + " não existe.");


    return srcFile;
  }
  public static void processFile(File inputFile) throws Exception{
    CommonTokenStream tokens = getTokenStreamFromFile(inputFile);
    ParseTree parseTree = getParseTreeFromTokenStream(tokens);

    Visitor forrest = new Visitor();
    Program prog = (Program)forrest.visit(parseTree);

    prog.accept(
      new PrettyPrintVisitor()
    );
  }
  public static CommonTokenStream getTokenStreamFromFile(File inputFile){
    String fileContents = "";
    try{
      fileContents = getFileContents(inputFile);
    }catch(Exception e){}

    System.out.println("Arquivo lido com sucesso");

    PT_IMSPCLexer lexLuthor = new PT_IMSPCLexer(
      new ANTLRInputStream(
        fileContents
      )
    );
    CommonTokenStream tokens = new CommonTokenStream(lexLuthor);

    return tokens;
  }
  public static ParseTree getParseTreeFromTokenStream(CommonTokenStream tokens){

    PT_IMSPCParser parser = new PT_IMSPCParser(tokens);

    ParseTree ctx = parser.goal();

    return ctx;
  }
  public static String getFileContents(File file) throws IOException{
    byte[] encoded = encoded = Files.readAllBytes(file.toPath());

    return new String(encoded);
  }
}

class Visitor extends PT_IMSPCBaseVisitor<Object>{

	public Object visitGoal(PT_IMSPCParser.GoalContext ctx) {
		System.out.println("Visitou goal");
		MainClass main = (MainClass) this.visit(ctx.getChild(0));
		ClassDeclList classDecs = new ClassDeclList();
		for (PT_IMSPCParser.ClassDeclarationContext classDecl : ctx.classDeclaration()) {
			classDecs.addElement((ClassDecl) this.visit(classDecl));
		}
		Program program = new Program(main, classDecs);
		return program;
	}


	public Object visitMainClass(PT_IMSPCParser.MainClassContext ctx) {
		System.out.println("Visitou main class");
		Identifier identifier1 = new Identifier(ctx.identifier(0).getText());
		Identifier identifier2 = new Identifier(ctx.identifier(1).getText());
		Statement statement = (Statement) this.visit(ctx.statement());
		MainClass main = new MainClass(identifier1, identifier2, statement);
		return main;
	}


	public Object visitMethodDeclaration(PT_IMSPCParser.MethodDeclarationContext ctx) {
		Formal formal1 = (Formal) this.visit(ctx.formal(0));
		Type type = formal1.t;
		Identifier identifier1 = formal1.i;
		FormalList formalList = new FormalList();
		VarDeclList varDecList = new VarDeclList();
		StatementList statementList = new StatementList();
		Exp exp = (Exp) this.visit(ctx.expr());
		for (PT_IMSPCParser.FormalContext formal : ctx.formal()) {
			formalList.addElement((Formal) this.visit(formal));
		}
		for (PT_IMSPCParser.VarDeclarationContext varDec : ctx.varDeclaration()) {
			varDecList.addElement((VarDecl) this.visit(varDec));
		}
		for (PT_IMSPCParser.StatementContext st : ctx.statement()) {
			statementList.addElement((Statement) this.visit(st));
		}
		MethodDecl methoDecl = new MethodDecl(type, identifier1, formalList, varDecList, statementList, exp);
		return methoDecl;
	}


	public Object visitStatement(PT_IMSPCParser.StatementContext ctx) {
		System.out.println("Statment");
		Statement statement = null;
		if (ctx.getChild(0).getText().equals("{")) {
			System.out.println("{ STATMENT* }");
			StatementList sts = new StatementList();
			for (PT_IMSPCParser.StatementContext statment : ctx.statement()) {
				sts.addElement((Statement) this.visit(statment));
			}
			statement = new Block(sts);

		} else if (ctx.getChild(0).getText().equals("if")) {
			System.out.println("if");
			Exp exp1 = (Exp) this.visit(ctx.expr(0));
			Statement statement1 = (Statement) this.visit(ctx.statement(0));
			Statement statement2 = (Statement) this.visit(ctx.statement(1));
			statement = new If(exp1, statement1, statement2);

		} else if (ctx.getChild(0).getText().equals("while")) {
			System.out.println("Rule 3");
			Exp exp1 = (Exp) this.visit(ctx.expr(0));
			Statement statement1 = (Statement) this.visit(ctx.statement(0));
			statement = new While(exp1, statement1);

		} else if (ctx.getChild(0).getText().equals("System.out.println")) {
			System.out.println("Rule 4");
			statement = new Print((Exp) this.visit(ctx.expr(0)));

		} else if (ctx.getChild(1).getText().equals("=")) {
			System.out.println("Rule 5");
			Identifier identifier1 = (Identifier) this.visit(ctx.identifier());
			Exp exp1 =  (Exp) this.visit(ctx.expr(0));
			statement = new Assign(identifier1, exp1);

		} else {
			System.out.println("Rule 6");
			Identifier identifier1 = (Identifier) this.visit(ctx.identifier());
			Exp exp1 =  (Exp) this.visit(ctx.expr(0));
			Exp exp2 =  (Exp) this.visit(ctx.expr(1));
			statement = new ArrayAssign(identifier1, exp1, exp2);
		}

		return statement;
	}


	public Object visitType(PT_IMSPCParser.TypeContext ctx) {
		Type type = null;
		if (ctx.getChild(0).getText().equals("int")) {
			if(ctx.getChildCount() > 1){
				type = new IntArrayType();
			}else{
				type = new IntegerType();
			}
		} else if (ctx.getChild(0).getText().equals("boolean")) {
			type = new BooleanType();
		} else {
			type = new IdentifierType(ctx.getText());
		}
		return type;
	}


	public Object visitVarDeclaration(PT_IMSPCParser.VarDeclarationContext ctx) {
		Formal formal = (Formal) this.visit(ctx.formal());
		Type type = formal.t;
		Identifier identifier = formal.i;
		VarDecl varDecl = new VarDecl(type, identifier);
		return varDecl;
	}


	public Object visitIdentifier(PT_IMSPCParser.IdentifierContext ctx) {
		System.out.println("Identificador" + ctx.getText());
		Identifier identifier = new Identifier(ctx.getText());
		return identifier;
	}


	public Object visitExpr(PT_IMSPCParser.ExprContext ctx) {
		System.out.println("Expr");
		Exp exp = null;
		if (ctx.getChild(0).getClass().equals(PT_IMSPCParser.ExprContext.class)) {
			if (ctx.getChild(1).getText().equals(".")) {
				if (ctx.getChild(2).getText().equals("length")) {
					System.out.println("expr '.' 'length'");
					exp = new ArrayLength((Exp) this.visit(ctx.expr(0)));
				} else {
					System.out.println("expr . identifier()");

					List<PT_IMSPCParser.ExprContext> expsContext = ctx.expr();
					List<Exp> exps = new ArrayList<Exp>();
					for (PT_IMSPCParser.ExprContext exprContext : expsContext) {
						exps.add((Exp) this.visit(exprContext));
					}
					Exp first = exps.get(0);
					exps.remove(0);
					ExpList explist = new ExpList();
					for (Exp exp2 : exps) {
						explist.addElement(exp2);
					}
					exp = new Call(first, new Identifier(ctx.identifier().getText()), explist);
				}
			} else if (ctx.getChild(1).getText().equals("[")) {
				Exp e1 = (Exp) this.visit(ctx.expr(0));
				Exp e2 = (Exp) this.visit(ctx.expr(1));
				exp = new ArrayLookup(e1, e2);
			} else {
				Exp e1 = (Exp) this.visit(ctx.expr(0));
				Exp e2 = (Exp) this.visit(ctx.expr(1));

				switch (ctx.op.getText()) {
				case "&&":
					System.out.println("expr " + ctx.op.getText());
					exp = new And(e1, e2);
					break;
				case "<":
					System.out.println("expr " + ctx.op.getText());
					exp = new LessThan(e1, e2);
					break;
				case "+":
					System.out.println("expr " + ctx.op.getText());
					exp = new Plus(e1, e2);
					break;
				case "-":
					System.out.println("expr " + ctx.op.getText());
					exp = new Minus(e1, e2);
					break;
				case "*":
					System.out.println("expr " + ctx.op.getText());
					;
					exp = new Times(e1, e2);
					break;
				}
			}
		} else if (ctx.INTEGER_LITERAL() != null) {
			exp = new IntegerLiteral(Integer.parseInt(ctx.INTEGER_LITERAL().getText()));
		} else if (ctx.identifier() != null) {
			System.out.println("identifier");
			exp = new IdentifierExp(ctx.identifier().getText());
		} else if (ctx.getChild(0).getText().equals("new")) {
			if (ctx.getChild(1).getText().equals("int")) {
				System.out.println("'new''int''['expr']'");
				exp = new NewArray((Exp) this.visit(ctx.expr(0)));
			} else {
				System.out.println("'new' identifier '('')'");
				exp = new NewObject(new Identifier(ctx.identifier().getText()));
			}
		} else if (ctx.getChild(0).getText().equals("!")) {
			System.out.println("'!' expr");
			exp = new Not((Exp) this.visit(ctx.expr(0)));
		} else if (ctx.getChild(0).getText().equals("(")) {
			System.out.println("'(' expr ')'");
			exp = (Exp) this.visit(ctx.expr(0));
		} else if (ctx.getText().equals("true")) {
			System.out.println("true");
			exp = new True();
		} else if (ctx.getText().equals("false")) {
			System.out.println("false");
			exp = new False();
		} else if (ctx.getText().equals("this")) {
			System.out.println("this");
			exp = new This();
		}
		return exp;
	}


	public Object visitClassDeclaration(PT_IMSPCParser.ClassDeclarationContext ctx) {

		Identifier identifier1 = (Identifier) this.visit(ctx.identifier(0));
		Identifier identifier2 = null;
		if (ctx.identifier().size() > 1) {
			identifier2 = (Identifier) this.visit(ctx.identifier(1));
		}
		VarDeclList varDecs = new VarDeclList();
		MethodDeclList metDecs = new MethodDeclList();
		for (PT_IMSPCParser.VarDeclarationContext varDec : ctx.varDeclaration()) {
			varDecs.addElement((VarDecl) this.visit(varDec));
		}
		for (PT_IMSPCParser.MethodDeclarationContext metDec : ctx.methodDeclaration()) {
			metDecs.addElement((MethodDecl) this.visit(metDec));
		}
		ClassDecl classDecl = null;
		if(identifier2 != null){
			classDecl = new ClassDeclExtends(identifier1, identifier2, varDecs, metDecs);
		}else{
			classDecl = new ClassDeclSimple(identifier1, varDecs, metDecs);
		}

		return classDecl;
	}


	public Object visitFormal(PT_IMSPCParser.FormalContext ctx) {
		Type type = (Type) this.visit(ctx.type());
		Identifier identifier1 = (Identifier) this.visit(ctx.identifier());
		Formal formal = new Formal(type, identifier1);
		return formal;
	}
}
