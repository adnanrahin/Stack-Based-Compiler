package interpreter_assignment_103;

public class Block extends Statement {

	SList slist;

	public Block(SList slist) {
		this.slist = slist;
	}

	void printParseTree(String indent) {
		String indent1 = indent + " ";

		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <block>");
		slist.printParseTree(indent1 + " ");
	}

}
