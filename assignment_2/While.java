package assignment_2;

public class While extends Statement {

	Expr expr;
	Statement statement;

	public While(Expr expr, Statement statement) {
		this.expr = expr;
		this.statement = statement;
	}
	
	public void printParseTree(String indent) {
		String indent1 = indent + " ";

		IO.displayln(indent + indent.length() + " <while>");
		IO.displayln(indent1 + indent1.length() + "( ");
		expr.printParseTree(indent1);
		IO.displayln(indent1 + indent1.length() + " )");
		statement.printParseTree(indent1);
	}
}
