package interpreter;

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
		return false;
	}

	boolean isSub() {
		return false;
	}

	Val Eval(Hashtable<String, Val> state) {
		return term.Eval(state);
	}
}