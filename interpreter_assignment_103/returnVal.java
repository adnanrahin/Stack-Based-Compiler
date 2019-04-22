package interpreter_assignment_103;

public class returnVal extends Var {
	public void printParseTree(String indent) {
		String indent1 = indent + " ";

		super.printParseTree(indent);
		IO.displayln("");
		IO.displayln(indent1 + indent1.length() + " returnVal");
	}
}