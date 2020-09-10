package interpeter;

import java.util.*;

class ParameterList {
	LinkedList<Parameter> parameterList;

	ParameterList(LinkedList<Parameter> pl) {
		parameterList = pl;
	}

	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <parameter list>");
		for (Parameter p : parameterList)
			p.printParseTree(indent + " ");
	}

	void M(Hashtable<String, Val> state, LinkedList<Val> params) {
		if (params.size() == parameterList.size()) {
			int i = 0;
			for (Parameter param : parameterList) {
				state.put(param.id.id, params.get(i++));
			}
		}
	}
}