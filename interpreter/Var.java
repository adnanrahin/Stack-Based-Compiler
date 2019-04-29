package interpreter;
import java.util.*;

abstract class Var
{
	void printParseTree(String indent)
	{
		IO.display(indent + indent.length() + " <var>");
	}
}