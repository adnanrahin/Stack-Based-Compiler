package assignment_2;

import java.util.LinkedList;

public class FunDefList {

	LinkedList<FunDef> fundeflist;

	public FunDefList(LinkedList<FunDef> fundeflist) {
		this.fundeflist = fundeflist;
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
