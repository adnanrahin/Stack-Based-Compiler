package interpreter_assignment_103;

import java.util.*;

class Expr {
	LinkedList<BoolTermItem> boolTermItemList;

	Expr(LinkedList<BoolTermItem> btItemList) {
		boolTermItemList = btItemList;
	}

	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <expr>");
		for (BoolTermItem bt : boolTermItemList)
			bt.printParseTree(indent + " ");
	}

	Val Eval(Hashtable<String, Val> state) {
		boolean temp = true;

		if (!boolTermItemList.isEmpty()) {

			if (boolTermItemList.get(0) != null) {

				Val val = boolTermItemList.get(0).Eval(state);

				if (val instanceof BoolVal) {
					temp = ((BoolVal) val).val;
				} else {
					if (boolTermItemList.size() == 1)
						return val;
					else
						return null;
				}

				for (int i = 1; i < boolTermItemList.size(); i++) {

					if (boolTermItemList.get(i) != null) {
						val = boolTermItemList.get(i).Eval(state);

						if (val instanceof BoolVal) {
							temp = temp || ((BoolVal) val).val;
						} else {
							System.out.println("BUG TRACING: ");
							return null;
						}
					} else
						return null;
				}
				return new BoolVal(temp);
			}
			return null;
		}
		return null;
	}
}