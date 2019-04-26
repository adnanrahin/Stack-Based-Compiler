package interpreter_assignment_103;

import java.util.*;

class DivPrimaryItem extends PrimaryItem

// Represents "/ <primary>"

{
	// Primary primary; inherited from PrimaryItem

	DivPrimaryItem(Primary p) {
		primary = p;
	}

	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " /");
		primary.printParseTree(indent);
	}

	boolean isMul() {
		return false;
	}

	boolean isDiv() {
		return true;
	}

	Val Eval(Hashtable<String, Val> state, Val termVal) {
		termVal = primary.Eval(state);
		if (termVal instanceof BoolVal) {
			System.out.println("Error: / operator cannot be applied to " + termVal);
		}
		return termVal;
	}
}