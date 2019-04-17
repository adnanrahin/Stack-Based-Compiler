package assignment_2;

public class FunCallStatement extends Statement_backup{

	FunCall funCall;
	
	public FunCallStatement(FunCall funCall){
		this.funCall = funCall;
	}
	
	public void printParseTree(String indent) {
		String indent1 = indent + " ";

		IO.displayln(indent + indent.length() + " <Fun Call Statement>");
		funCall.printParseTree(indent1);
		IO.displayln(indent1 + indent1.length() + " ;");

	}
	
}
