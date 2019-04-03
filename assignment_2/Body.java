package assignment_2;

public class Body {

	SList slist;

	public Body(SList slist) {
		this.slist = slist;
	}

	void printParseTree(String indent) {
		String indent1 = indent + " ";
		IO.display(indent + indent.length() + " <Body>");
		IO.displayln(indent1 + indent1.length() + "{ ");
		slist.printParseTree(indent1);
		IO.displayln(indent1 + indent1.length() + " }");
	}
	
}
