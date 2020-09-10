package interpeter;

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

	Val Eval(Hashtable<String, Val> state) {
		Val termVal = primary.Eval(state);
		if (termVal instanceof BoolVal) {
			System.out.println("Error: / operator cannot be applied to " + termVal);
			return null;
		}
		return termVal;
	}
}