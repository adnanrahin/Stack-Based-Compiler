package interpreter_assignment_103;

class DivPrimaryItem extends PrimaryItem {
	DivPrimaryItem(Primary p) {
		primary = p;
	}

	public void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " /");
		primary.printParseTree(indent);
	}
}
