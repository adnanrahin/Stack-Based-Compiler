package interpreter_assignment_103;

import java.util.*;

public class FunName {
	Id id;

	FunName(Id ident) {
		id = ident;
	}

	void printParseTree(String indent) {
		IO.display(indent + indent.length() + " <fun name>");
		id.printParseTree();
	}
}