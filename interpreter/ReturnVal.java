package interpreter;

import java.util.*;

class ReturnVal extends Var {
	void printParseTree(String indent) {
		String indent1 = indent + " ";

		super.printParseTree(indent);
		IO.displayln("");
		IO.displayln(indent1 + indent1.length() + " returnVal");
	}

	Val Eval(HashMap<String, Val> state) {
		
		Val val = state.get("returnVal");
		
		if (val == null) {
			System.out.println("returnVal does not have a value");
			return null;
		}
		return val;

	}
}