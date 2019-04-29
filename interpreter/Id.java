package interpreter;

import java.util.*;

class Id {
	String id;

	Id(String ident) {
		id = ident;
	}

	void printParseTree() {
		IO.displayln(" " + id);
	}

	Val Eval(HashMap<String, Val> state) {
		Val idVal = state.get(id);
		if (idVal != null)
			return idVal.cloneVal();
		else {
			System.out.println("variable " + id + " does not have a value");
			return null;
		}
	}

}