package ast;
import visitor.Visitor;
import visitor.TypeVisitor;

public abstract class Type {
  String typeName = "";

  public Type(){}
  public Type(String tn){
    this.typeName = tn;
  }
  public abstract void accept(Visitor v);
  public abstract Type accept(TypeVisitor v) throws Exception ;
  public String getName()
  {
    return this.typeName;
  }
}
