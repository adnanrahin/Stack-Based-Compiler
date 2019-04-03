package assignment_2;

import java.util.LinkedList;

public class EList {

	LinkedList<E> eList;

	public EList() {

		// default constructor

	}

	public EList(LinkedList<E> eList) {
		this.eList = eList;
	}
	
	public void eprintParseTree(String indent) {
		
		IO.displayln(indent + indent.length() + " <E List>");
		for (E a : eList)
			a.printParseTree(indent + " ");
		
	}
	
}
