package interpreter_assignment_103;

public class Print extends Statement {

	Expr expr;

	public Print(Expr expr) {
		this.expr = expr;
	}

	public void printParseTree(String indent) {
		String indent1 = indent + " ";

		IO.displayln(indent + indent.length() + " <print>");
		IO.displayln(indent1 + indent1.length() + " ;");
		expr.printParseTree(indent1);

	}

}
