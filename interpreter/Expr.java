package interpreter;

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

				BoolTermItem btitem = boolTermItemList.get(0);

				Val val = btitem.Eval(state);

				if (val instanceof BoolVal) {
					temp = ((BoolVal) val).val;
				} else {
					if (boolTermItemList.size() == 1)
						return val;
					else {
						System.out.println("Error: || operator cannot be applied to " + val);
						return null;
					}
				}
				for (int i = 1; i < boolTermItemList.size(); i++) {

					btitem = boolTermItemList.get(i);

					if (btitem != null) {

						val = btitem.Eval(state);

						if (val instanceof BoolVal) {
							temp = temp || ((BoolVal) val).val;
						} else {
							System.out.println("Error: || operator cannot be applied to " + val);
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