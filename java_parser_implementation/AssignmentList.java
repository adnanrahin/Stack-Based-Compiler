package java_parser_implementation;

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