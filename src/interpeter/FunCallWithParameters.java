package interpeter;

import java.util.*;

class FunCallWithParameters extends FunCall {
	// FunName funName; inherited from FunCall

	ExprList exprList;

	FunCallWithParameters(FunName fName, ExprList eList) {
		funName = fName;
		exprList = eList;
	}

	void printParseTree(String indent) {
		String indent1 = indent + " ";

		super.printParseTree(indent1);
		exprList.printParseTree(indent1 + " ");
	}

	Val Eval(Hashtable<String, Val> state) {
		Id id = funName.id;
		FunDef funDef = Parser.funDeftable.get(id.id);

		if (id == null || funDef == null)
			return null;

		Hashtable<String, Val> newStateTable = new Hashtable<>();
		LinkedList<Val> params = new LinkedList<Val>();

		exprList.M(state, params);
		Header header = funDef.header;
		header.M(newStateTable, params);

		Body body = funDef.body;
		body.M(newStateTable);

		return newStateTable.get("returnVal");
	}
}