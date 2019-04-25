package interpreter_assignment_103;

import java.util.*;

class SubTermItem extends TermItem

// Represents "- <term>"

{
	// Term term; inherited from TermItem

	SubTermItem(Term t) {
		term = t;
	}

	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " -");
		term.printParseTree(indent);
	}

	boolean isAdd() {
		return false;
	}

	boolean isSub() {
		return true;
	}

	Val Eval(Hashtable<String, Val> state, Val eVal) {
		eVal = term.Eval(state);
		return eVal;
	}
}