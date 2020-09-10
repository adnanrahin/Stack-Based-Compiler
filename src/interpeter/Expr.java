package interpeter;

import java.util.*;

class Expr {
	LinkedList<BoolTermItem> boolTermItemList;

	Expr(LinkedList<BoolTermItem> btList) {
		boolTermItemList = btList;
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

				BoolTermItem bt = boolTermItemList.get(0);

				Val val = bt.Eval(state);

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

					bt = boolTermItemList.get(i);

					if (bt != null) {

						val = bt.Eval(state);

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