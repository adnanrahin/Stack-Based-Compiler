package assignment_2;

public abstract class Statement {

	void printParseTree(String indent) {
		IO.display(indent + indent.length() + " <statement>");
	}

}
