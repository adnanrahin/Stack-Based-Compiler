package java_parser_implementation;

public class OrBoolTermItem extends BoolTermItem {

	OrBoolTermItem(BoolTerm bt) {
		boolTerm = bt;
	}

	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " ||");
		boolTerm.printParseTree(indent);
	}
}
