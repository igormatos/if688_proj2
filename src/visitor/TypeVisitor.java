package visitor;

import ast.*;

public interface TypeVisitor {
  public Type visit(Program n)  throws Exception;
  public Type visit(MainClass n) throws Exception;
  public Type visit(ClassDeclSimple n) throws Exception;
  public Type visit(ClassDeclExtends n) throws Exception;
  public Type visit(VarDecl n)  throws Exception;
  public Type visit(MethodDecl n) throws Exception;
  public Type visit(Formal n)  throws Exception;
  public Type visit(IntArrayType n)  throws Exception;
  public Type visit(BooleanType n)  throws Exception;
  public Type visit(IntegerType n)  throws Exception;
  public Type visit(IdentifierType n)  throws Exception;
  public Type visit(Block n) throws Exception;
  public Type visit(If n) throws Exception;
  public Type visit(While n) throws Exception;
  public Type visit(Print n)  throws Exception;
  public Type visit(Assign n)  throws Exception;
  public Type visit(ArrayAssign n)  throws Exception;
  public Type visit(And n)  throws Exception;
  public Type visit(LessThan n)  throws Exception;
  public Type visit(Plus n)  throws Exception;
  public Type visit(Minus n) throws Exception;
  public Type visit(Times n) throws Exception;
  public Type visit(ArrayLookup n) throws Exception;
  public Type visit(ArrayLength n) throws Exception;
  public Type visit(Call n) throws Exception;
  public Type visit(IntegerLiteral n) throws Exception;
  public Type visit(True n) throws Exception;
  public Type visit(False n) throws Exception;
  public Type visit(IdentifierExp n) throws Exception;
  public Type visit(This n) throws Exception;
  public Type visit(NewArray n) throws Exception;
  public Type visit(NewObject n) throws Exception;
  public Type visit(Not n) throws Exception;
  public Type visit(Identifier n) throws Exception;
}
