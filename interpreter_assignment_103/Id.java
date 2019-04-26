package interpreter_assignment_103;

import java.util.*;

class Id {
	String id;

	Id(String ident) {
		id = ident;
	}

	void printParseTree() {
		IO.displayln(" " + id);
	}

	Val Eval(HashMap<String, Val> state, Val eVal) {
		eVal = state.get(id);

		if (eVal == null) {
			System.out.println("variable " + id + " does not have a value");
		}

		return eVal;
	}
}