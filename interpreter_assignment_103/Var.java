package interpreter_assignment_103;

public abstract class Var {
	public void printParseTree(String indent) {
		IO.display(indent + indent.length() + " <var>");
	}
}