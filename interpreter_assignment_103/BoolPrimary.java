package interpreter_assignment_103;

import java.util.*;

abstract class BoolPrimary {
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <boolPrimary>");
	}

	abstract Val Eval(Hashtable<String, Val> state);
}