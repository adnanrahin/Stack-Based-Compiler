package assignment_2;

public class Header {

	FunName funName;
	ParameterList parameterList;

	public Header(FunName funName, ParameterList parameterList) {
		this.funName = funName;
		this.parameterList = parameterList;
	}

	void printParseTree(String indent) {
		String indent1 = indent + " ";

		IO.displayln(indent1 + indent.length() + " <Header>");
		funName.printParseTree(indent1);
		parameterList.printParseTree(indent1);

	}

}
