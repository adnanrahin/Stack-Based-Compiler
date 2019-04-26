package interpreter_assignment_103;

import java.util.*;

class InvPrimary extends Primary {
	Primary primary;

	InvPrimary(Primary p) {
		primary = p;
	}

	void printParseTree(String indent) {
		String indent1 = indent + " ";

		super.printParseTree(indent);
		IO.displayln("");
		IO.displayln(indent1 + indent1.length() + " !");
		primary.printParseTree(indent1);
	}

	Val Eval(Hashtable<String, Val> state) {
		Val val = primary.Eval(state);

		if (val instanceof BoolVal)
			return new BoolVal(val.floatVal() == 0.0);

		else {
			System.out.println("Error: ! operator cannot be applied to " + primary.Eval(state));
			return null;
		}
	}
}