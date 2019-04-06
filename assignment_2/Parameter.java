package assignment_2;

public class Parameter {

	String id;
	E e;

	public Parameter(String id, E e) {

		this.id = id;
		this.e = e;

	}

	public Parameter(String id) {
		this.id = id;
	}

	public void printParseTree(String indent) {
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent.length() + " <parameter> " + id);
	}

}
