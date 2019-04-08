package java_parser_implementation;

public abstract class Var {
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <var>");
	}

}
