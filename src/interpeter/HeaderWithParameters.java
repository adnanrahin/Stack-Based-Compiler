package interpeter;

import java.util.*;

class HeaderWithParameters extends Header {
	// FunName funName; inherited from Header

	ParameterList parameterList;

	HeaderWithParameters(FunName fName, ParameterList pList) {
		funName = fName;
		parameterList = pList;
	}

	void printParseTree(String indent) {
		super.printParseTree(indent);
		parameterList.printParseTree(indent + " ");
	}

	void M(Hashtable<String, Val> state, LinkedList<Val> params) {
		parameterList.M(state, params);
	}
}