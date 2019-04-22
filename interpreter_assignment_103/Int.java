package interpreter_assignment_103;

public class Int extends Primary {
	int val;

	Int(int i) {
		val = i;
	}

	public void printParseTree(String indent) {
		super.printParseTree(indent);
		IO.displayln(" " + val);
	}

}