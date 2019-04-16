package java_parser_implementation;

public class SingleBoolPrimaryItem extends BoolPrimaryItem {

	SingleBoolPrimaryItem(BoolPrimary bp) {
		boolPrimary = bp;
	}

	void printParseTree(String indent) {
		boolPrimary.printParseTree(indent);
	}

}
