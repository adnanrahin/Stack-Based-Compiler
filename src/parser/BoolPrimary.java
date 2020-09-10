package parser;

public class BoolPrimary {

	E e;
	CompOp compOp;

	public BoolPrimary(E e, CompOp compOp) {
		this.e = e;
		this.compOp = compOp;
	}

	public BoolPrimary(E e) {
		this.e = e;
	}

	void printParseTree(String indent) {
		String indent1 = indent + " ";

		IO.displayln(indent + indent.length() + " <boolPrimary>");
		e.printParseTree(indent1);
		compOp.printParseTree(indent1);
		e.printParseTree(indent1);
	}

}
