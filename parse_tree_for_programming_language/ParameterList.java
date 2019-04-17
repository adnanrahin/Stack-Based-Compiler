package parse_tree_for_programming_language;

import java.util.LinkedList;

public class ParameterList {

	LinkedList<Parameter> parameterslist;

	public ParameterList(LinkedList<Parameter> parameterslist) {

		this.parameterslist = parameterslist;

	}

	public ParameterList() {
		// default constructor
	}

	public void printParseTree(String indent) {
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent.length() + " <parameter List>");
		for(Parameter a: parameterslist) {
			a.printParseTree(indent1);
		}
	}

}
