package interpreter_assignment_103;

public class HeaderWithParameters extends Header {
	
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
}