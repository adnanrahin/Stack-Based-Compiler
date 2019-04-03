package assignment_2;


class Parenthesized extends Primary {
	E e;

	Parenthesized(E exp) {
		e = exp;
	}

	void printParseTree(String indent) {
		super.printParseTree(indent);
		IO.displayln("");
		e.printParseTree(indent + " ");
	}

}
