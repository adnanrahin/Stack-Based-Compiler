package assignment_2;

public class ExprRightSide extends RightSide {

	Expr expr;

	public ExprRightSide(Expr expr) {

		this.expr = expr;

	}

	public void printParseTree(String indent) {

		super.printParseTree(indent);
		IO.display(indent + indent.length() + " <expr right side>");
	}

}
