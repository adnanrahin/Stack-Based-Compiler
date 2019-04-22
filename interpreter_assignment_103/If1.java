package interpreter_assignment_103;

public class If1 extends Cond {

	If1(Expr e, Statement s) {
		expr = e;
		statement1 = s;
	}

	void printParseTree(String indent) {
		String indent2 = indent + "  ";

		super.printParseTree(indent);
		IO.displayln(indent2 + indent2.length() + " if");
		expr.printParseTree(indent2);
		statement1.printParseTree(indent2);
	}
}