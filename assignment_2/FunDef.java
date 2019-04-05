package assignment_2;

public class FunDef {

	Header header;
	Body body;

	public FunDef(Header header, Body body) {
		this.header = header;
		this.body = body;
	}

	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <fun def> ");
		header.printParseTree(indent);
		body.printParseTree(indent);
	}

}
