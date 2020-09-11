package interpeter_final;

import java.util.HashMap;
import java.util.LinkedList;

class EList {
	LinkedList<E> eList;

	EList(LinkedList<E> el) {
		eList = el;
	}

	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <E list>");
		for (E e : eList)
			e.printParseTree(indent + " ");
	}

	LinkedList<Val> Eval(HashMap<String, Val> state) {
		LinkedList<Val> elist = new LinkedList<Val>();
		for (E e : eList) {
			elist.add(e.Eval(state));
		}
		return elist;
	}
}