package parser;

import java.util.LinkedList;

public class Expr {

	LinkedList<BoolTerm> boolTerms;

	public Expr(LinkedList<BoolTerm> boolTerms) {
		this.boolTerms = boolTerms;

	}

	public void printParseTree(String indent) {

		IO.displayln(indent + indent.length() + "<boolTerm>");

		for (BoolTerm b : boolTerms)
			b.printParseTree(indent + " ");

	}

}
