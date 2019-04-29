package interpreter;
import java.util.*;

abstract class Header
{
	FunName funName;
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <header>");
		funName.printParseTree(indent+" ");
	}

	abstract void M(Hashtable<String, Val> newState, LinkedList<Val> params);
}