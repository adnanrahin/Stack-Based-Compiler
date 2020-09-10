package interpeter;

import java.util.*;

class VarPrimary extends Primary {
	Var var;

	VarPrimary(Var v) {
		var = v;
	}

	void printParseTree(String indent) {
		super.printParseTree(indent);
		IO.displayln("");
		var.printParseTree(indent + " ");
	}

	Val Eval(Hashtable<String, Val> state) {
		if (var instanceof IdVar) {
			Id id = ((IdVar) var).id;
			return state.get(id.id);
		}

		else if (var instanceof ReturnVal) {
			return state.get("returnVal");
		}

		else if (var instanceof ArrayVar) {
			// TODO: continue;
		}

		return null;
	}
}