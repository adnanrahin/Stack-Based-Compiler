package parse_tree_for_programming_language;

import java.util.*;

class AssignmentList {
	LinkedList<Assignment> assignmentList;

	AssignmentList(LinkedList<Assignment> al) {
		assignmentList = al;
	}

	void printParseTree(String indent) {
		for (Assignment a : assignmentList)
			a.printParseTree(indent);
	}
}