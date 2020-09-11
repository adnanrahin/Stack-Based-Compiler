package interpeter_final;

import java.util.LinkedList;

class ExprList
{
	LinkedList<Expr> exprList;

	ExprList(LinkedList<Expr> el)
	{
		exprList = el;
	}
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <expr list>");
		for ( Expr expr : exprList )
			expr.printParseTree(indent+" ");
	}
}