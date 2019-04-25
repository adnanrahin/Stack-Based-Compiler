package interpreter_assignment_103;

import java.util.*;

class SingleTermItem extends TermItem

// Represents the first <term> in <E>

{
	// Term term; inherited from TermItem

	SingleTermItem(Term t) {
		term = t;
	}

	void printParseTree(String indent) {
		term.printParseTree(indent);
	}

	boolean isAdd() {
		return true;
	}

	boolean isSub() {
		return true;
	}

	Val Eval(Hashtable<String, Val> state, Val eVal) {
		eVal = term.Eval(state);
		return eVal;
	}
}