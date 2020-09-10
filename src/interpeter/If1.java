package interpeter;

import java.util.*;

class If1 extends Cond {
	// Expr expr; inherited from Cond
	// Statement statement1; inherited from Cond

	If1(Expr e, Statement s) {
		expr = e;
		statement1 = s;
	}

	void printParseTree(String indent) {
		String indent2 = indent + "  ";

		super.printParseTree(indent);
		IO.displayln(indent2 + indent2.length() + " if");
		expr.printParseTree(indent2);
		statement1.printParseTree(indent2);
	}

	void M(Hashtable<String, Val> state) {
		Val val = expr.Eval(state);
		if (val instanceof BoolVal) {
			if (((BoolVal) val).val) {
				statement1.M(state);
			}
		}
		return;
	}
}