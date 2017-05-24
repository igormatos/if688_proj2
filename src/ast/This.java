package ast;
import visitor.Visitor;
import visitor.TypeVisitor;

public class This extends Exp {
  public void accept(Visitor v) {
    v.visit(this);
  }

   public Type accept(TypeVisitor v) throws Exception  {
    return v.visit(this);
  }
}
