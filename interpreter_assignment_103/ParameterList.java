package interpreter_assignment_103;
import java.util.*;

class ParameterList
{
	LinkedList<Parameter> parameterList;

	ParameterList(LinkedList<Parameter> pl)
	{
		parameterList = pl;
	}
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <parameter list>");
		for ( Parameter p : parameterList )
			p.printParseTree(indent+" ");
	}

	void M(Hashtable<String, Val> state, List<Val> list) {
		if (parameterList.size() != list.size())
			return;
		
		for (int i = 0; i < parameterList.size(); i++) {
			Parameter parameter = parameterList.get(i);
			Val val = list.get(i);
			state.put(parameter.id.id, val);
		}
	}
}