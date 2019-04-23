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
				if ((val instanceof IntVal) || (val instanceof FloatVal)) {
					if (!(val instanceof IntVal))
						isInt = false;

					Double temp = val.floatVal();

					for (int i = 1; i < termItemList.size(); i++) {

						TermItem termItem = termItemList.get(i);

						if (termItem != null) {
							val = termItem.Eval(state);

							if (!(val instanceof BoolVal)) {
								if (!(val instanceof IntVal))
									isInt = false;

								if (termItem.isAdd())
									temp += val.floatVal();
								else if (termItem.isSub())
									temp -= val.floatVal();
								else
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
					if (termItemList.size() == 1)
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