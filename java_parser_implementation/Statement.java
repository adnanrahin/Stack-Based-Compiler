package java_parser_implementation;

public abstract class Statement {
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <statement>");
	}
}
