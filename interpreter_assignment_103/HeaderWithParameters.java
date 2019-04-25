package interpreter_assignment_103;
import java.util.*;

class HeaderWithParameters extends Header
{
	//FunName funName; inherited from Header

	ParameterList parameterList;

	HeaderWithParameters(FunName fName, ParameterList pList)
	{
		funName = fName;
		parameterList = pList;
	}
	
	void printParseTree(String indent)
	{
		super.printParseTree(indent);
		parameterList.printParseTree(indent+" ");
	}

	@Override
	void M(Hashtable<String, Val> state, List<Val> list) {
		parameterList.M(state, list);
	}
}