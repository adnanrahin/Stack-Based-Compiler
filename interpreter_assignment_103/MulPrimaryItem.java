package interpreter_assignment_103;
import java.util.*;

class MulPrimaryItem extends PrimaryItem

// Represents "* <primary>"

{
	// Primary primary; inherited from PrimaryItem

	MulPrimaryItem(Primary p)
	{
		primary = p;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " *");
		primary.printParseTree(indent);
	}

	@Override
	boolean isMul() {
		return true;
	}

	@Override
	boolean isDiv() {
		return false;
	}

	@Override
	Val Eval(Hashtable<String, Val> state) {
		return primary.Eval(state);
	}
}