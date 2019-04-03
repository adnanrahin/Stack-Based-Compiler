package assignment_2;

public class IdVar extends Statement {

	String id;
	E e;

	public IdVar() {
		// default constructor
	}

	public IdVar(String id, E e) {

		this.id = id;
		this.e = e;

	}

	public IdVar(String id) {

		this.id = id;

	}

	public IdVar(E e) {

		this.e = e;
	}

	public void printParseTree(String indent) {

		String indent1 = indent + " ";

		IO.displayln(indent + indent.length() + " <Id Var>");
		IO.displayln(indent1 + indent1.length() + " " + id);
		e.printParseTree(indent1);

	}

}
