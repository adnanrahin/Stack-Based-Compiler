package assignment_2;

class Floatp extends Primary {
	float val;

	Floatp(float f) {
		val = f;
	}

	void printParseTree(String indent) {
		super.printParseTree(indent);
		IO.displayln(" " + val);
	}

}
