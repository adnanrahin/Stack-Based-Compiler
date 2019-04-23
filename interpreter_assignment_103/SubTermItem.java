package interpreter_assignment_103;
import java.util.*;

class SubTermItem extends TermItem

// Represents "- <term>"

{
	// Term term; inherited from TermItem

	SubTermItem(Term t)
	{
		term = t;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " -");
		term.printParseTree(indent);
	}

	@Override
	boolean isAdd() {
		return false;
	}

	@Override
	boolean isSub() {
		return true;
	}

	@Override
	Val Eval(Hashtable<String, Val> state) {
		return term.Eval(state);
	}
}