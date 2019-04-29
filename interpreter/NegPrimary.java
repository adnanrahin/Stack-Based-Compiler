package interpreter;

import java.util.*;

class NegPrimary extends Primary {
	Primary primary;

	NegPrimary(Primary p) {
		primary = p;
	}

	void printParseTree(String indent) {
		String indent1 = indent + " ";

		super.printParseTree(indent);
		IO.displayln("");
		IO.displayln(indent1 + indent1.length() + " -");
		primary.printParseTree(indent1);
	}

	Val Eval(Hashtable<String, Val> state) {

		if (primary.Eval(state) != null) {
			if (!(primary.Eval(state) instanceof BoolVal)) {
				Double temp = primary.Eval(state).floatVal();

				if (primary.Eval(state) instanceof IntVal)
					return new IntVal(-temp.intValue());
				return new FloatVal(-temp.floatValue());

			} else if (primary.Eval(state) instanceof BoolVal) {
				System.out.println("unary - operator cannot be applied to " + primary.Eval(state));
				return null;
			}

		}
		return null;
	}
}