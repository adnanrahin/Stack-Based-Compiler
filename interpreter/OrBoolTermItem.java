package interpreter;
import java.util.*;

class OrBoolTermItem extends BoolTermItem

// Represents "|| <boolTerm>"

{
	// BoolTerm boolTerm; inherited from BoolTermItem

	OrBoolTermItem(BoolTerm bt)
	{
		boolTerm = bt;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " ||");
		boolTerm.printParseTree(indent);
	}

	Val Eval(Hashtable<String, Val> state) {
		return boolTerm.Eval(state);
	}
}