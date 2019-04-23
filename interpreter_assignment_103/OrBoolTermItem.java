package interpreter_assignment_103;
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

	@Override
	Val Eval(Hashtable<String, Val> state) {
		return boolTerm.Eval(state);
	}
}