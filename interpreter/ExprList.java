package interpreter;
import java.util.*;

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

	void M(Hashtable<String, Val> state, LinkedList<Val> params) {
		for (int i = 0; i < exprList.size(); i++) {
			Expr expr = exprList.get(i);
			Val val = expr.Eval(state);
			params.add(val);
		}
	}
}