package interpreter_final;

import java.util.*;

class ArrayVar extends Var {
	ArrayName arrayName;
	EList eList;

	ArrayVar(ArrayName aName, EList el) {
		arrayName = aName;
		eList = el;
	}

	void printParseTree(String indent) {
		String indent1 = indent + " ";
		String indent2 = indent + "  ";

		super.printParseTree(indent);
		IO.displayln("");
		IO.displayln(indent1 + indent1.length() + " <array var>");
		arrayName.printParseTree(indent2);
		eList.printParseTree(indent2);
	}

	Val Eval(HashMap<String, Val> state) {
		Val val = state.get(arrayName.id.id);

		try {
			if (val != null) {
				if (val instanceof ArrayVal) {
					return ((ArrayVal) val).Eval(eList.Eval(state));
				} else {
					System.out.println("array variable " + arrayName.id.id + " does not have a value");
					return null;
				}
			}
		} catch (NullPointerException e) {
			System.out.println("variable " + arrayName.id.id + " has a non-array value: " + val);
			return null;
		}
		return val;
	}

	void M(HashMap<String, Val> state, RightSide rightSide) // interpret assignment <array var> = <right side>
	{
		Val val = state.get(arrayName.id.id);

		if (val != null) {
			if (val instanceof ArrayVal) {
				HashMap<LinkedList<Val>, Val> map = new HashMap<LinkedList<Val>, Val>();
				map.put(eList.Eval(state), rightSide.Eval(state));
				((ArrayVal) val).M(map);
			}
			return;
		}
		return;
	}
}