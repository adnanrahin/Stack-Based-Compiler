package interpeter;
import java.util.*;

abstract class BoolPrimaryItem
{
	BoolPrimary boolPrimary;

	abstract void printParseTree(String indent);

	abstract Val Eval(Hashtable<String, Val> state);
}