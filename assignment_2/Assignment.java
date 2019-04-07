package assignment_2;

class Assignment extends Statement {
	/*
	 * String id; // variable on the left side of the assignment E e; // expression
	 * on the right side of the assignment
	 * 
	 * Assignment(String s, E exp) { id = s; e = exp; }
	 */

//	⟨assignment⟩ → ⟨var⟩ "=" ⟨right side⟩ ";" 

	Var var;
	RightSide rightSide;

	public Assignment(Var var, RightSide rightSide) {
		this.var = var;
		this.rightSide = rightSide;
	}

	public void printParseTree(String indent) {
		String indent1 = indent + " ";

		IO.displayln(indent + indent.length() + " <assignment>");
		var.printParseTree(indent1);
		rightSide.printParseTree(indent1);
	}

}