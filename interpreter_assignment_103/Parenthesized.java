package interpreter_assignment_103;

class Parenthesized extends Primary {
	Expr e;

	Parenthesized(Expr e) {
		this.e = e;
	}

	void printParseTree(String indent) {
		super.printParseTree(indent);
		IO.displayln("");
		e.printParseTree(indent + " ");
	}

}
