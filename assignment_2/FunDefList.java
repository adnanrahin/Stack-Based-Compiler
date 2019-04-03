package assignment_2;

import java.util.LinkedList;

public class FunDefList {

	LinkedList<FunDef> fundeflist;

	public FunDefList(LinkedList<FunDef> fd) {
		fundeflist = fd;
	}

	public FunDefList() {
		// default constructor
	}

	public void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <fun def list>");
		for (FunDef a : fundeflist)
			a.printParseTree(indent + " ");
	}

}
