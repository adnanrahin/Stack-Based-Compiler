package assignment_2;

public abstract class Statement {

	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <statement>");
	}

}
