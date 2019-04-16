package java_parser_implementation;

public class Print extends Statement {

	Expr expr;

	public Print(Expr expr) {
		this.expr = expr;
	}

	public void printParseTree(String indent) {
		String indent1 = indent + " ";

		IO.displayln(indent + indent.length() + " <print>");
		expr.printParseTree(indent1);
		IO.displayln(indent1 + indent1.length() + " ;");

	}

}
