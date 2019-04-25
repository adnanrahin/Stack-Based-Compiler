package interpreter_assignment_103;

import java.util.*;

class SinglePrimaryItem extends PrimaryItem

// Represents the first <primary> in <term>

{
	// Primary primary; inherited from PrimaryItem

	SinglePrimaryItem(Primary p) {
		primary = p;
	}

	void printParseTree(String indent) {
		primary.printParseTree(indent);
	}

	boolean isMul() {
		return false;
	}

	boolean isDiv() {
		return false;
	}

	Val Eval(Hashtable<String, Val> state, Val termVal) {
		termVal = primary.Eval(state);
		return termVal;
	}
}