package assignment_2;

public class ArrayVar extends Var {

	ArrayName arrayName;

	public ArrayVar(ArrayName arrayName) {
		this.arrayName = arrayName;
	}

	void printParseTree(String indent) {
		String indent1 = indent + " ";
		IO.display(indent + indent.length() + " <array var>");
		arrayName.printParseTree(indent1);
	}

}
