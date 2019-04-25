package interpreter_assignment_103;

class ReturnVal extends Var {
	void printParseTree(String indent) {
		String indent1 = indent + " ";

		super.printParseTree(indent);
		IO.displayln("");
		IO.displayln(indent1 + indent1.length() + " returnVal");
	}
}