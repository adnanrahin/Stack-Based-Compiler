package interpreter;

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

				BoolPrimaryItem bpitem = boolPrimaryItemList.get(0);

				Val val = bpitem.Eval(state);

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

					bpitem = boolPrimaryItemList.get(i);

					if (bpitem != null) {

						val = bpitem.Eval(state);

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
			}
			return null;
		}
		return null;
	}
}