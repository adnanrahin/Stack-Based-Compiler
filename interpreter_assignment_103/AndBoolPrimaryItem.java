package interpreter_assignment_103;

public class AndBoolPrimaryItem extends BoolPrimaryItem {

	AndBoolPrimaryItem(BoolPrimary bp) {
		boolPrimary = bp;
	}

	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " &&");
		boolPrimary.printParseTree(indent);
	}

}
