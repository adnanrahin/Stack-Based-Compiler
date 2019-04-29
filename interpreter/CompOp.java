package interpreter;

public class CompOp {

	String id;

	CompOp(String id) {
		this.id = id;
	}

	void printParseTree(String indent) {
		IO.display(indent + indent.length() + " <comp op>");
		IO.display(indent + indent.length() + " " + id);
	}

}
