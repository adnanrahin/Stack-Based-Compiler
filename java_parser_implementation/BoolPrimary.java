package java_parser_implementation;

public abstract class BoolPrimary {
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <boolPrimary>");
	}
}
