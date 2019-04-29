package interpreter;

import java.util.*;

class Assignment extends Statement {
	Var var; // variable on the left side of the assignment
	RightSide rightSide; // right side of the assignment

	Assignment(Var v, RightSide rs) {
		var = v;
		rightSide = rs;
	}

	void printParseTree(String indent) {
		String indent1 = indent + " ";
		String indent2 = indent + "  ";

		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <assignment>");
		var.printParseTree(indent2);
		IO.displayln(indent2 + indent2.length() + " =");
		rightSide.printParseTree(indent2);
	}

	void M(Hashtable<String, Val> state) {
		if (rightSide instanceof ExprRightSide) {
			Expr expr = ((ExprRightSide) rightSide).expr;
			if (var instanceof IdVar) {
				Id id = ((IdVar) var).id;
				state.put(id.id, expr.Eval(state));
			}

			else if (var instanceof ReturnVal) {
				state.put("returnVal", expr.Eval(state));
			}

			else if (var instanceof ArrayVar) {
				// TODO: continue;
			}
		}
	}
}