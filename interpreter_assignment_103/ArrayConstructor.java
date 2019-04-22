package interpreter_assignment_103;

public class ArrayConstructor extends RightSide {

	EList eList;

	public ArrayConstructor(EList eList) {
		this.eList = eList;
	}

	public void printParseTree(String indent) {
		String indent1 = indent + " ";

		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <array constructor>");
		eList.printParseTree(indent1 + " ");
	}

}
