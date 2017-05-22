package visitor;

import ast.*;

public class SymbolTableBuilder extends BuildSymbolTableVisitor {


  // classes
  public void visit(ClassDeclSimple n) {
    // identificador.string, null parent
    newCurrClass(n.i.s, null);
    super.visit(n);
    addCurrClass();
  }

  public void newCurrClass(String id, String parent){
    currClass = new Class(id, parent);
  }
  public void addCurrClass(){
    symbolTable.addClass(currClass);
  }

}
