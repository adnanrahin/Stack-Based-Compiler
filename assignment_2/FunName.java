package assignment_2;

public class FunName {

	String id;

	public FunName(String id) {
		this.id = id;
	}

	public void printParseTree(String indent) {

		String indent1 = indent + " ";
		IO.display(indent + indent.length() + " <FunName>");
		IO.displayln(indent1 + indent1.length() + " " + id);

	}

}
