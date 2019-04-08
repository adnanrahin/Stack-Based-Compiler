package java_parser_implementation;

public class ArrayVar extends Var {

	ArrayName arrayName;
	EList eList;
	public ArrayVar(ArrayName arrayName) {
		this.arrayName = arrayName;
	}
	
	public ArrayVar(ArrayName arrayName, EList eList) {
		this.arrayName = arrayName;
		this.eList = eList;
	}

	void printParseTree(String indent) {
		String indent1 = indent + " ";
		IO.display(indent + indent.length() + " <array var>");
		arrayName.printParseTree(indent1);
	}

}
