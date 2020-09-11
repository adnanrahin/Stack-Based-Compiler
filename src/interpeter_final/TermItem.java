package interpeter_final;

import java.util.HashMap;

abstract class TermItem
{
	Term term;

	abstract void printParseTree(String indent);
	abstract Val Eval(HashMap<String,Val> state, Val eVal);
}