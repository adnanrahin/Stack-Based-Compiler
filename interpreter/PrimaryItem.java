package interpreter;
import java.util.*;

abstract class PrimaryItem
{
	Primary primary;

	abstract void printParseTree(String indent);
	abstract Val Eval(Hashtable<String, Val> state);
}