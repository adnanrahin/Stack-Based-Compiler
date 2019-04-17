package parse_tree_for_programming_language;

public class IdVar extends Var {

	String id;

	public IdVar(String id) {
		this.id = id;
	}

	public void printParseTree(String indent) {
		String indent1 = indent + " ";
		IO.displayln(indent + indent.length() + " <Id Var>");
		IO.displayln(indent1 + indent1.length() + " " + id);
	}

}
