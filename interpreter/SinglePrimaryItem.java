package interpreter;
import java.util.*;

class SinglePrimaryItem extends PrimaryItem

// Represents the first <primary> in <term>

{
	// Primary primary; inherited from PrimaryItem

	SinglePrimaryItem(Primary p)
	{
		primary = p;
	}

	void printParseTree(String indent)
	{
		primary.printParseTree(indent);
	}
	
	@Override
	boolean isMul() {
		return false;
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