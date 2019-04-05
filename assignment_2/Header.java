package assignment_2;

public class Header {

	FunName funName;
	ParameterList parameterList;

	public Header(FunName funName, ParameterList parameterList) {
		this.funName = funName;
		this.parameterList = parameterList;
	}

	public Header(ParameterList parameterList) {
		this.parameterList = parameterList;
	}

	void printParseTree(String indent) {
		String indent1 = indent + " ";

		IO.display(indent + indent.length() + " <fun name>");
		funName.printParseTree(indent1);
		IO.displayln(indent1 + indent1.length() + "( ");
		parameterList.printParseTree(indent1);
		IO.displayln(indent1 + indent1.length() + " )");

	}

}
