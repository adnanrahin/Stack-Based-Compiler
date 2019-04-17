package parse_tree_for_programming_language;

import java.util.LinkedList;

public class ExprList {

	LinkedList<Expr> exprList;

	public ExprList(LinkedList<Expr> exprList) {
		this.exprList = exprList;
	}

	public void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <Expr List>");
		for (Expr p : exprList)
			p.printParseTree(indent + " ");
	}

}
