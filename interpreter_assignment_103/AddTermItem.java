package interpreter_assignment_103;

import java.util.*;

public class AddTermItem extends TermItem

{

	AddTermItem(Term t) {
		term = t;
	}

	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " +");
		term.printParseTree(indent);
	}

	boolean isAdd() {
		return true;
	}

	boolean isSub() {
		return false;
	}

	Val Eval(Hashtable<String, Val> state, Val eVal) {
		eVal = term.Eval(state);
		if (eVal instanceof BoolVal)
			System.out.println("Error: + operator cannot be applied to " + eVal);
		return eVal;
	}
}