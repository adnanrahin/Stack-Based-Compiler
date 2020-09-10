package parser;

public class FunName {

	String id;

	public FunName(String id) {
		this.id = id;
	}

	public void printParseTree(String indent) {

		String indent1 = indent + " ";
		IO.displayln(indent + indent.length() + " <Fun Name> " + id);
		IO.display(indent1);
	}

}
