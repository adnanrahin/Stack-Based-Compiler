package interpreter_assignment_103;

class Parameter {
	Id id;

	Parameter(Id ident) {
		id = ident;
	}

	void printParseTree(String indent) {
		IO.display(indent + indent.length() + " <parameter>");
		id.printParseTree();
	}
}