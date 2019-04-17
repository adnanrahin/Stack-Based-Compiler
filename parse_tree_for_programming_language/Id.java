package parse_tree_for_programming_language;

import java.util.*;

class Id extends Primary {
	String id;

	Id(String ident) {
		id = ident;
	}

	void printParseTree(String indent) {
		super.printParseTree(indent);
		IO.displayln(" " + id);
	}

}