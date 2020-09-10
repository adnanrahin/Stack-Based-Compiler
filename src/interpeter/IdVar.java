package interpeter;

import java.util.*;

class IdVar extends Var {
	Id id;

	IdVar(Id ident) {
		id = ident;
	}

	void printParseTree(String indent) {
		String indent1 = indent + " ";

		super.printParseTree(indent);
		IO.displayln("");
		IO.display(indent1 + indent1.length() + " <id var>");
		id.printParseTree();
	}
}