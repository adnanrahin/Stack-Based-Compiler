package java_parser_implementation;

public class SingleBoolTermItem extends BoolTermItem {

	SingleBoolTermItem(BoolTerm bt) {
		boolTerm = bt;
	}

	void printParseTree(String indent) {
		boolTerm.printParseTree(indent);
	}
}