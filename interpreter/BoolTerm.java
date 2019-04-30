package interpreter;

import java.util.*;

class BoolTerm {
	LinkedList<BoolPrimaryItem> boolPrimaryItemList;

	BoolTerm(LinkedList<BoolPrimaryItem> bpList) {
		boolPrimaryItemList = bpList;
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

				BoolPrimaryItem bp = boolPrimaryItemList.get(0);

				Val val = bp.Eval(state);

				if (val instanceof BoolVal) {
					temp = ((BoolVal) val).val;
				} else {
					if (boolPrimaryItemList.size() == 1)
						return val;
					else {
						System.out.println("Error: && operator cannot be applied to " + val);
						return null;
					}
				}

				for (int i = 1; i < boolPrimaryItemList.size(); i++) {

					bp = boolPrimaryItemList.get(i);

					if (bp != null) {

						val = bp.Eval(state);

						if (val instanceof BoolVal) {
							temp = temp && ((BoolVal) val).val;
						} else {
							System.out.println("Error: && operator cannot be applied to " + val);
							return null;
						}
					} else
						return null;
				}
				return new BoolVal(temp);
			} else
				return null;
		}
		return null;
	}
}