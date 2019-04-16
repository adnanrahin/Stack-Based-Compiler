package java_parser_implementation;

public class NegPrimary extends Primary {
	Primary primary;

	NegPrimary(Primary p) {
		primary = p;
	}

	void printParseTree(String indent) {
		String indent1 = indent + " ";

		super.printParseTree(indent);
		IO.displayln("");
		IO.displayln(indent1 + indent1.length() + " -");
		primary.printParseTree(indent1);
	}
}