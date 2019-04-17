package parse_tree_for_programming_language;

import java.util.*;

public class SinglePrimaryItem extends PrimaryItem

// Represents the first <primary> in <term>

{
	// Primary primary; inherited from PrimaryItem

	SinglePrimaryItem(Primary p) {
		primary = p;
	}

	void printParseTree(String indent) {
		primary.printParseTree(indent);
	}

}