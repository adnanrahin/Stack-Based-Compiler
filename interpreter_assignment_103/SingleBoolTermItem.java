package interpreter_assignment_103;

import java.util.*;

class SingleBoolTermItem extends BoolTermItem

// Represents the first <boolTerm> in <Expr>

{
	// BoolTerm boolTerm; inherited from BoolTermItem

	SingleBoolTermItem(BoolTerm bt) {
		boolTerm = bt;
	}

	void printParseTree(String indent) {
		boolTerm.printParseTree(indent);
	}

	Val Eval(Hashtable<String, Val> state) {
		return boolTerm.Eval(state);
	}
}