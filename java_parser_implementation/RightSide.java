package java_parser_implementation;

public abstract class RightSide {

	void printParseTree(String indent) {
		IO.display(indent + indent.length() + " <right side>");
	}
	
}
