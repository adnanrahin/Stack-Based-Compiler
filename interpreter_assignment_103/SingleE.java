package interpreter_assignment_103;

public class SingleE extends BoolPrimary {
	E e;

	SingleE(E e_) {
		e = e_;
	}

	void printParseTree(String indent) {
		super.printParseTree(indent);
		e.printParseTree(indent + " ");
	}
}