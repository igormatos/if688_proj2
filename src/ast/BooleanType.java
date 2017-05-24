package ast;
import visitor.Visitor;
import visitor.TypeVisitor;

public class BooleanType extends Type {

  public BooleanType(){
    super("boolean");
  }
  public void accept(Visitor v) {
    v.visit(this);
  }

   public Type accept(TypeVisitor v) throws Exception  {
    return v.visit(this);
  }
}
