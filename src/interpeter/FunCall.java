package interpeter;
import java.util.*;

abstract class FunCall
{
	FunName funName;
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <fun call>");
		funName.printParseTree(indent+" ");
	}

	abstract Val Eval(Hashtable<String, Val> state);
}