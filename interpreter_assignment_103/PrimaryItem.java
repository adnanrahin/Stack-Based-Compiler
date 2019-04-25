package interpreter_assignment_103;

import java.util.*;

abstract class PrimaryItem {
	Primary primary;

	abstract void printParseTree(String indent);

	abstract boolean isMul();

	abstract boolean isDiv();

	abstract Val Eval(Hashtable<String, Val> state, Val termVal);
}