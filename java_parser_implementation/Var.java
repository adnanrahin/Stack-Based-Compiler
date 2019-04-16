package java_parser_implementation;

public abstract class Var {
	void printParseTree(String indent) {
		IO.display(indent + indent.length() + " <var>");
	}
}