package interpreter_assignment_103;

import java.util.LinkedList;

public class ParameterList {

	LinkedList<Parameter> parameterslist;

	public ParameterList(LinkedList<Parameter> parameterslist) {

		this.parameterslist = parameterslist;

	}

	public void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <parameter list>");
		for (Parameter p : parameterslist)
			p.printParseTree(indent + " ");
	}

}
