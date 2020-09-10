package interpeter;

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

	void M(Hashtable<String, Val> state) {
		for (Assignment a : assignmentList)
			a.M(state);
	}
}