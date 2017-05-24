package symboltable;
import ast.Type;

public interface IScope{
  public boolean addVar(String id, Type type);
}
