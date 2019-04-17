package parse_tree_for_programming_language;

public class FunCallPrimary extends Primary {

	FunCall funCall;

	public FunCallPrimary(FunCall funCall) {
		this.funCall = funCall;
	}

	void printParseTree(String indent) {
		super.printParseTree(indent);
		funCall.printParseTree(indent);
	}

}
