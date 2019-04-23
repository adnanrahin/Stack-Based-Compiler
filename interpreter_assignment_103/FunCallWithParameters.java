package interpreter_assignment_103;

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

	@Override
	Val Eval(Hashtable<String, Val> state) {
		Id id = funName.id;
		if (id == null)
			return null;

		FunDef funDef = Parser.fundeftable.get(id.id);
		if (funDef == null)
			return null;

		LinkedList<Val> params = new LinkedList<Val>();
		exprList.M(state, params);

		Hashtable<String, Val> newState = new Hashtable<>();
		Header header = funDef.header;
		header.M(newState, params);

		Body body = funDef.body; // get the body of main function
		body.M(newState);

		return newState.get("returnVal");
	}
}