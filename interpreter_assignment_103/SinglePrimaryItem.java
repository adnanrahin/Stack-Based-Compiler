package interpreter_assignment_103;

public class SinglePrimaryItem extends PrimaryItem {
	SinglePrimaryItem(Primary p) {
		primary = p;
	}

	public void printParseTree(String indent) {
		primary.printParseTree(indent);
	}

}