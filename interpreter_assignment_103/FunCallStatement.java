package interpreter_assignment_103;

public class FunCallStatement extends Statement {

	FunCall funCall;

	public FunCallStatement(FunCall funCall) {
		this.funCall = funCall;
	}

	public void printParseTree(String indent) {
		String indent1 = indent + " ";

		IO.displayln(indent + indent.length() + " <Fun Call Statement>");
		IO.displayln(indent1 + indent1.length() + " ;");
		funCall.printParseTree(indent1);

	}

}
