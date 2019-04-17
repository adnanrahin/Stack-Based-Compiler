package assignment_2;

public class FunDef {

	Header header;
	Body body;

	public FunDef(Header header, Body body) {
		this.header = header;
		this.body = body;
	}

	public FunDef(Header header) {
		this.header = header;
	}
	
	void printParseTree(String indent) {
		String indent1 = indent + " ";
		IO.displayln(indent + indent.length() + " <fun def> ");
		header.printParseTree(indent1);
		body.printParseTree(indent1);
	}

}
