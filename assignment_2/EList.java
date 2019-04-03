package assignment_2;

import java.util.LinkedList;

public class EList {

	LinkedList<E> eList;

	public EList(LinkedList<E> eList) {
		this.eList = eList;
	}
	
	public void printParseTree(String indent) {
		
		IO.displayln(indent + indent.length() + " <E List>");
		for (E a : eList)
			a.printParseTree(indent + " ");
		
	}
	
}
