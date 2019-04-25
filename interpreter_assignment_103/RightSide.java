package interpreter_assignment_103;

abstract class RightSide {
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <right side>");
	}
}