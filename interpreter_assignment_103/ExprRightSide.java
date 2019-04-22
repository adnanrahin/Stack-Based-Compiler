package interpreter_assignment_103;

public class ExprRightSide extends RightSide {

	Expr expr;

	public ExprRightSide(Expr expr) {

		this.expr = expr;

	}

	public void printParseTree(String indent) {
		String indent1 = indent + " ";

		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <expr right side>");
		expr.printParseTree(indent1 + " ");
	}

}
