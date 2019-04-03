package assignment_2;

public class Expr {
	String id;
	E e;
	
	public Expr(String id, E e) {
		
		this.e = e;
		this.id = id;
	
	}
	
	public void printParseTree(String indent) {
		String indent1 = indent + " ";

		IO.displayln(indent + indent.length() + " <expr>");
		IO.displayln(indent1 + indent1.length() + " " + id);
		IO.displayln(indent1 + indent1.length() + " ,");
		e.printParseTree(indent1);
	}
	
}
