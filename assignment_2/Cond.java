package assignment_2;

public class Cond extends Statement {

	Expr expr;
	Statement statement;

	public Cond(Expr expr, Statement statement) {
		this.expr = expr;
		this.statement = statement;
	}

	void printParseTree(String indent) {
		String indent1 = indent + " ";

		IO.displayln(indent + indent.length() + " <if>");
		IO.displayln(indent1 + indent1.length() + "( ");
		expr.printParseTree(indent1);
		IO.displayln(indent1 + indent1.length() + " )");
		statement.printParseTree(indent1);
		IO.displayln(indent1 + indent1.length() + "else");
		statement.printParseTree(indent1);
	}

}
