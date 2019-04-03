package assignment_2;

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

		for(Parameter a: parameterslist)
			a.printParseTree(indent);
		
	}

}
