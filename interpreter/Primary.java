package interpreter;
import java.util.*;

abstract class Primary
{
	void printParseTree(String indent)
	{
		IO.display(indent + indent.length() + " <primary>");
	}

	abstract Val Eval(Hashtable<String, Val> state);
}