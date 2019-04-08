package java_parser_implementation;

public class ArrayConstructor extends RightSide {

	EList eList;

	public ArrayConstructor(EList eList) {
		this.eList = eList;
	}
	
	void printParseTree(String indent) {
		IO.display(indent + indent.length() + " <array constructor>");
		IO.display(indent + indent.length() + " new ");
		IO.display(indent + indent.length() + "[ ");
		eList.printParseTree(indent);
		IO.display(indent + indent.length() + " ]");

	}

}
