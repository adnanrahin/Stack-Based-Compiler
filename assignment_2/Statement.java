package assignment_2;

public abstract class Statement {

	public void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <statement>");
	}

}
