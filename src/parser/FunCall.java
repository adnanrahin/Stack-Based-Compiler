package parser;

public class FunCall {

	FunName funName;
	ExprList exprList;

	public FunCall(FunName funName, ExprList exprList) {
		this.exprList = exprList;
		this.funName = funName;
	}

	void printParseTree(String indent) {
		IO.display(indent + indent.length() + " <fun call>");
		funName.printParseTree(indent);
		exprList.printParseTree(indent);
	}
}
