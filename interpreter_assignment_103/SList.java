package interpreter_assignment_103;

import java.util.LinkedList;

public class SList {

	LinkedList<Statement> statements;

	public SList(LinkedList<Statement> statements) {
		this.statements = statements;
	}

	public void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <s list>");
		for (Statement s : statements)
			s.printParseTree(indent + " ");
	}

}
