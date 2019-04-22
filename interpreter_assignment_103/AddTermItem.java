package interpreter_assignment_103;

public class AddTermItem extends TermItem {
	
	AddTermItem(Term t) {
		term = t;
	}

	public void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " +");
		term.printParseTree(indent);
	}

}
