package assignment_2;

public class BoolPrimary {

	E e;

	public BoolPrimary(E e) {
		this.e = e;
	}
	
	void printParseTree(String indent) {
		String indent1 = indent + " ";

		IO.displayln(indent + indent.length() + " <boolPrimary>");
		e.printParseTree(indent1);
	}

}
