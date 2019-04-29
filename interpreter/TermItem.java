package interpreter;
import java.util.*;

abstract class TermItem
{
	Term term;

	abstract void printParseTree(String indent);
	
	abstract boolean isAdd();
	
	abstract boolean isSub();

	abstract Val Eval(Hashtable<String, Val> state);
}