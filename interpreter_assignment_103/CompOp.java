package interpreter_assignment_103;

public class CompOp {
	
	String id;
	
	public CompOp(String id) {
	
		this.id = id;
		
	}
	
	public void printParseTree(String indent) {
		IO.display(indent + indent.length() + " <comp op>");
		IO.display(indent + indent.length() + " " + id);
	}
	
}
