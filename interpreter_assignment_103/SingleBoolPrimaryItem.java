package interpreter_assignment_103;

public class SingleBoolPrimaryItem extends BoolPrimaryItem {

	SingleBoolPrimaryItem(BoolPrimary bp) {
		boolPrimary = bp;
	}

	void printParseTree(String indent) {
		boolPrimary.printParseTree(indent);
	}

}
