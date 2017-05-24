package ast;
import visitor.Visitor;
import visitor.TypeVisitor;

public class False extends Exp {
  public void accept(Visitor v) {
    v.visit(this);
  }

   public Type accept(TypeVisitor v) throws Exception  {
    return v.visit(this);
  }
}
