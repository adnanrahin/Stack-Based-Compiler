package parser;

public class Statement {

	Statement_backup statement;

	public Statement(Statement_backup statement) {
		this.statement = statement;
	}

	void printParseTree(String indent) {
		String indent1 = indent + " ";
		IO.displayln(indent + indent.length() + " <statement>");
		statement.printParseTree(indent1);
	}
}
