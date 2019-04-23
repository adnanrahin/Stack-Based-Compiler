package interpreter_assignment_103;

import java.util.*;

class Term {
	LinkedList<PrimaryItem> primaryItemList;

	Term(LinkedList<PrimaryItem> pItemList) {
		primaryItemList = pItemList;
	}

	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <term>");
		for (PrimaryItem p : primaryItemList)
			p.printParseTree(indent + " ");
	}

	Val Eval(Hashtable<String, Val> state) {
		boolean isInt = true;

		if (primaryItemList.get(0) != null || !primaryItemList.isEmpty()) {

			Val val = primaryItemList.get(0).Eval(state);

			if (val != null) {
				if ((val instanceof IntVal) || (val instanceof FloatVal)) {
					if (!(val instanceof IntVal))
						isInt = false;

					Double temp = val.floatVal();

					for (int i = 1; i < primaryItemList.size(); i++) {

						PrimaryItem termItem = primaryItemList.get(i);

						if (termItem != null) {
							val = termItem.Eval(state);

							if (!(val instanceof BoolVal)) {
								if (!(val instanceof IntVal))
									isInt = false;

								if (termItem.isMul())
									temp *= val.floatVal();
								else if (termItem.isDiv()) {
									if (val.floatVal() == 0) {
										System.out.println("Error: division by 0");
										return null;
									} else
										temp /= val.floatVal();
								} else
									return null;
							} else
								return null;
						} else
							return null;
					}

					if (isInt)
						return new IntVal(temp.intValue());

					return new FloatVal(temp.doubleValue());
				} else {
					if (primaryItemList.size() == 1)
						return val;
					else
						return null;
				}
			}
			return null;
		}
		return null;
	}
}