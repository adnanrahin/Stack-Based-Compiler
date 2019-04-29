package interpreter;
import java.util.*;

abstract class BoolTermItem
{
	BoolTerm boolTerm;

	abstract void printParseTree(String indent);

	abstract Val Eval(Hashtable<String, Val> state);
}