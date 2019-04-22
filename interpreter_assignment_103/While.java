package interpreter_assignment_103;

public class While extends Statement {

	Expr expr;
	Statement statement;

	public While(Expr expr, Statement statement) {
		this.expr = expr;
		this.statement = statement;
	}

	public void printParseTree(String indent) {
		String indent1 = indent + " ";
		String indent2 = indent + "  ";

		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <while>");
		IO.displayln(indent2 + indent2.length() + " while");
		expr.printParseTree(indent2);
		statement.printParseTree(indent2);
	}
}
