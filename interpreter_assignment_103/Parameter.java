package interpreter_assignment_103;

public class Parameter {
	Id id;

	Parameter(Id id) {
		this.id = id;
	}

	void printParseTree(String indent) {
		IO.display(indent + indent.length() + " <parameter>");
		id.printParseTree();
	}
}
