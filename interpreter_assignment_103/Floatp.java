package interpreter_assignment_103;

public class Floatp extends Primary {
	double val;

	Floatp(double d) {
		val = d;
	}

	void printParseTree(String indent) {
		super.printParseTree(indent);
		IO.displayln(" " + val);
	}
}
