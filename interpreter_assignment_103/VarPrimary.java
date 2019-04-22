package interpreter_assignment_103;

public class VarPrimary extends Primary {

	Var var;

	public VarPrimary(Var var) {
		this.var = var;
	}

	public void printParseTree(String indent) {
		super.printParseTree(indent);
		IO.displayln("");
		var.printParseTree(indent + " ");
	}

}
