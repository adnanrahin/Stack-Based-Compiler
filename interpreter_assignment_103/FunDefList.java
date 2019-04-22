package interpreter_assignment_103;

import java.util.LinkedList;

public class FunDefList {

	LinkedList<FunDef> fundeflist;

	public FunDefList(LinkedList<FunDef> fundeflist) {
		this.fundeflist = fundeflist;
	}
	
	public void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <fun def list>");
		for (FunDef a : fundeflist) {
			a.printParseTree(indent + " ");
		}
	}

}
