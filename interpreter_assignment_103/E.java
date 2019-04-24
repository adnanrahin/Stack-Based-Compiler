package interpreter_assignment_103;

import java.util.*;

class E {
	LinkedList<TermItem> termItemList;

	E(LinkedList<TermItem> tItemList) {
		termItemList = tItemList;
	}

	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <E>");
		for (TermItem t : termItemList)
			t.printParseTree(indent + " ");
	}

	Val Eval(Hashtable<String, Val> state) {
		boolean isInt = true;

		if (termItemList.get(0) != null || !termItemList.isEmpty()) {

			Val val = termItemList.get(0).Eval(state);

			if (val != null) {
				TermItem termItem = termItemList.get(0);
				if ((val instanceof IntVal) || (val instanceof FloatVal)) {
					if (!(val instanceof IntVal))
						isInt = false;

					Double temp = val.floatVal();

					for (int i = 1; i < termItemList.size(); i++) {

						termItem = termItemList.get(i);

						if (termItem != null) {
							val = termItem.Eval(state);

							if (!(val instanceof BoolVal)) {
								if (!(val instanceof IntVal))
									isInt = false;

								if (termItem.isAdd())
									temp += val.floatVal();
								else if (termItem.isSub())
									temp -= val.floatVal();
								else {
									if (termItem.isAdd()) {
										System.out.println("Error:1 + operator cannot be applied to " + val);
									} else
										System.out.println("Error:1 + operator cannot be applied to " + val);
									return null;
								}
							} else {
								if (termItem.isAdd()) {
									System.out.println("Error:2 + operator cannot be applied to " + val);
								} else
									System.out.println("Error:2 - operator cannot be applied to " + val);
								return null;
							}
						} else {
							if (termItemList.get(0).isAdd()) {
								System.out.println("Error:3 + operator cannot be applied to " + val);
							} else
								System.out.println("Error:3 - operator cannot be applied to " + val);
							return null;
						}
					}

					if (isInt)
						return new IntVal(temp.intValue());

					return new FloatVal(temp.doubleValue());
				} else {
					if (termItemList.size() == 1)
						return val;
					else {
						if (termItem.isAdd()) {
							System.out.println("Error:4 - operator cannot be applied to " + val);
						} else
							System.out.println(
									"Error:4 + operator cannot be applied to " + termItemList.get(0).Eval(state));
						return null;
					}
				}
			} else {
				System.out.println("Error:5 " + "operator cannot be applied to " + val);
				return null;
			}
		} else {
			// System.out.println("Error: 6" + "operator cannot be applied to " + val);
			return null;
		}
	}
}