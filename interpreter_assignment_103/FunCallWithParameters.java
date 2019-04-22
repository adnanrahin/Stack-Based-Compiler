package interpreter_assignment_103;

public class FunCallWithParameters extends FunCall {

	ExprList exprList;

	FunCallWithParameters(FunName fName, ExprList eList) {
		funName = fName;
		exprList = eList;
	}

	void printParseTree(String indent) {
		String indent1 = indent + " ";

		super.printParseTree(indent1);
		exprList.printParseTree(indent1 + " ");
	}
}