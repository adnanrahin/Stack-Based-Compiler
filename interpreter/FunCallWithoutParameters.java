package interpreter;

import java.util.*;

class FunCallWithoutParameters extends FunCall {
	// FunName funName; inherited from FunCall

	FunCallWithoutParameters(FunName fName) {
		funName = fName;
	}

	void printParseTree(String indent) {
		super.printParseTree(indent + " ");
	}

	Val Eval(Hashtable<String, Val> state) {
		Id id = funName.id;
		FunDef funDef = Parser.funDeftable.get(id.id);

		if (id == null || funDef == null)
			return null;
		Hashtable<String, Val> newStateTable = new Hashtable<>();
		Body body = funDef.body;

		body.M(newStateTable);

		return newStateTable.get("returnVal");
	}
}