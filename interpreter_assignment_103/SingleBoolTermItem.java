package interpreter_assignment_103;

public class SingleBoolTermItem extends BoolTermItem {

	SingleBoolTermItem(BoolTerm bt) {
		boolTerm = bt;
	}

	void printParseTree(String indent) {
		boolTerm.printParseTree(indent);
	}
}