package parser;

import java.util.LinkedList;

public class BoolTerm {

	LinkedList<BoolPrimary> boolTerm;
	
	public BoolTerm(LinkedList <BoolPrimary> boolTerm) {
		
		this.boolTerm = boolTerm;
		
	}
	
	public void printParseTree(String indent) {
		
		IO.displayln(indent + indent.length() + "<boolTerm>");
		
		for(BoolPrimary b: boolTerm)
			b.printParseTree(indent + " ");
		
	}
	
}
