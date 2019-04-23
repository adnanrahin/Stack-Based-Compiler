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

	@Override
	boolean isMul() {
		return false;
	}

	@Override
	boolean isDiv() {
		return true;
	}

	@Override
	Val Eval(Hashtable<String, Val> state) {
		return primary.Eval(state);
	}
}