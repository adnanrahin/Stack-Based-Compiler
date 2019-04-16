package java_parser_implementation;

public class SingleE extends BoolPrimary {
	E e;

	SingleE(E e_) {
		e = e_;
	}

	void printParseTree(String indent) {
		super.printParseTree(indent);
		e.printParseTree(indent + " ");
	}
}