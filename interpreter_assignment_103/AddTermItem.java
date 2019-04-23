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

	@Override
	boolean isAdd() {
		return true;
	}

	@Override
	boolean isSub() {
		return false;
	}

	@Override
	Val Eval(Hashtable<String, Val> state) {
		return term.Eval(state);
	}
}