package parse_tree_for_programming_language;

public abstract class RightSide {

	void printParseTree(String indent) {
		IO.display(indent + indent.length() + " <right side>");
	}
	
}
