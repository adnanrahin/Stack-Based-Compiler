package assignment_2;

public class returnVal extends Var {

	String id;

	public returnVal(String id) {
		this.id = id;
	}

	void printParseTree(String indent) {
		String indent1 = indent + " ";
		IO.displayln(indent + indent.length() + " <returnVal>");
		IO.displayln(indent1 + indent1.length() + " " + id);
	}

}
