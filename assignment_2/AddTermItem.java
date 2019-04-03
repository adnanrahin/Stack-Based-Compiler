package assignment_2;

import java.util.*;

class AddTermItem extends TermItem

// Represents "+ <term>"

{
	// Term term; inherited from TermItem

	AddTermItem(Term t) {
		term = t;
	}

	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " +");
		term.printParseTree(indent);
	}

}
