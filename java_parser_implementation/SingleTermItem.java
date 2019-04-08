package java_parser_implementation;

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

}