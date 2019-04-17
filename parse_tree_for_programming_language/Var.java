package parse_tree_for_programming_language;

public abstract class Var {
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <var>");
	}

}
