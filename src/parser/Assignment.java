package parser;

class Assignment extends Statement_backup {

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