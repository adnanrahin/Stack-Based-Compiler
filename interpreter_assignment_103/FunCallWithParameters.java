package interpreter_assignment_103;

import java.util.*;

class FunCallWithParameters extends FunCall {

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
		if (id != null) {
			FunDef funDef = Parser.fundeftable.get(id.id);
			if (funDef != null) {
				List<Val> list = new ArrayList<Val>();
				exprList.M(state, list);
				Hashtable<String, Val> newState = new Hashtable<>();
				Header header = funDef.header;
				header.M(newState, list);
				Body body = funDef.body;
				body.M(newState);

				return newState.get("returnVal");
			}
			return null;
		}
		return null;
	}
}