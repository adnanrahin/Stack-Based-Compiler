package assignment_2;

class Assignment extends Statement{
	String id; // variable on the left side of the assignment
	E e; // expression on the right side of the assignment

	Assignment(String s, E exp) {
		id = s;
		e = exp;
	}

	void printParseTree(String indent) {
		String indent1 = indent + " ";

		IO.displayln(indent + indent.length() + " <assignment>");
		IO.displayln(indent1 + indent1.length() + " " + id);
		IO.displayln(indent1 + indent1.length() + " =");
		e.printParseTree(indent1);
	}

}