package interpreter_assignment_103;

public abstract class Statement {
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <statement>");
	}
}
