package interpreter_assignment_103;
import java.util.*;

class SingleTermItem extends TermItem

// Represents the first <term> in <E>

{
	// Term term; inherited from TermItem

	SingleTermItem(Term t)
	{
		term = t;
	}

	void printParseTree(String indent)
	{
		term.printParseTree(indent);
	}

	@Override
	boolean isAdd() {
		return false;
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