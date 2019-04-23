package interpreter_assignment_103;

import java.util.*;

class BoolTerm {
	LinkedList<BoolPrimaryItem> boolPrimaryItemList;

	BoolTerm(LinkedList<BoolPrimaryItem> bpItemList) {
		boolPrimaryItemList = bpItemList;
	}

	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <boolTerm>");
		for (BoolPrimaryItem bp : boolPrimaryItemList)
			bp.printParseTree(indent + " ");
	}

	Val Eval(Hashtable<String, Val> state) {

		boolean temp = true;

		if (!boolPrimaryItemList.isEmpty()) {

			if (boolPrimaryItemList.get(0) != null) {

				Val val = boolPrimaryItemList.get(0).Eval(state);

				if (val instanceof BoolVal) {
					temp = ((BoolVal) val).val;
				} else {
					if (boolPrimaryItemList.size() == 1)
						return val;
					else
						return null;
				}

				for (int i = 1; i < boolPrimaryItemList.size(); i++) {

					if (boolPrimaryItemList.get(i) != null) {
						val = boolPrimaryItemList.get(i).Eval(state);

						if (val instanceof BoolVal) {
							temp = temp && ((BoolVal) val).val;
						} else {
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
