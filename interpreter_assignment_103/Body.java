package interpreter_assignment_103;

public class Body {

	SList slist;

	public Body(SList slist) {
		this.slist = slist;
	}

	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <body>");
		slist.printParseTree(indent + " ");
	}

}
