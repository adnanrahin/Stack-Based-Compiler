package interpreter_assignment_103;

import java.util.*;

class Term {
	LinkedList<PrimaryItem> primaryItemList;

	Term(LinkedList<PrimaryItem> pItemList) {
		primaryItemList = pItemList;
	}

	public void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <term>");
		for (PrimaryItem p : primaryItemList)
			p.printParseTree(indent + " ");
	}

}