package java_parser_implementation;

public class Block extends Statement_backup {

	SList slist;

	public Block(SList slist) {
		this.slist = slist;
	}

	public void printParseTree(String indent) {
		String indent1 = indent + " ";
		IO.display(indent + indent.length() + " <block>");
		IO.displayln(indent1 + indent1.length() + "{ ");
		slist.printParseTree(indent1);
		IO.displayln(indent1 + indent1.length() + " }");
	}

}
