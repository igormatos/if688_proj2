package ast;
import visitor.Visitor;
import visitor.TypeVisitor;

public class IntegerType extends Type {
  public IntegerType(){
    super("int");
  }
  public void accept(Visitor v) {
    v.visit(this);
  }

   public Type accept(TypeVisitor v) throws Exception  {
    return v.visit(this);
  }
}
