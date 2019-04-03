package assignment_2;

public abstract class Var {

	IdVar idVar;
	ArrayVar arrayVar;

	public Var() {
		// TODO Auto-generated constructor stub
	}

	public Var(IdVar idVar, ArrayVar arrayVar) {

		this.idVar = idVar;
		this.arrayVar = arrayVar;

	}

	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <var>");
	}

}
