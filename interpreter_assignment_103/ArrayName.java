package interpreter_assignment_103;

public class ArrayName {
	Id id;

	ArrayName(Id ident) {
		id = ident;
	}

	public void printParseTree(String indent) {
		IO.display(indent + indent.length() + " <array name>");
		id.printParseTree();
	}
}