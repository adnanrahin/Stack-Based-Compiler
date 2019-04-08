package java_parser_implementation;

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

}