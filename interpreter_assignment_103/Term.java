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
		try {
			boolean isInt = true;

			if (primaryItemList.get(0) != null || !primaryItemList.isEmpty()) {

				Val val = primaryItemList.get(0).Eval(state, null);

				if (val != null) {
					if ((val instanceof IntVal) || (val instanceof FloatVal)) {
						if (!(val instanceof IntVal))
							isInt = false;

						Double temp = val.floatVal();

						for (int i = 1; i < primaryItemList.size(); i++) {

							PrimaryItem primaryItem = primaryItemList.get(i);

							if (primaryItem != null) {
								val = primaryItem.Eval(state, val);

								if (!(val instanceof BoolVal)) {
									if (!(val instanceof IntVal))
										isInt = false;

									if (primaryItem.isMul())
										temp *= val.floatVal();
									else if (primaryItem.isDiv()) {
										if (val.floatVal() == 0) {
											System.out.println("Error: division by 0");
											return null;
										} else
											temp /= val.floatVal();
									} else {
										return null;
									}
								} else {
									if (primaryItem.isMul()) {
										System.out.println("Error: * operator cannot be applied to " + val);
									} else {
										System.out.println("Error: / operator cannot be applied to " + val);
									}
									return null;
								}
							} else {
								if (primaryItemList.get(primaryItemList.size() - 1).isMul()) {
									System.out.println("Error: * operator cannot be applied to " + val);
								} else {
									System.out.println("Error: / operator cannot be applied to " + val);
								}
								return null;
							}
						}

						if (isInt)
							return new IntVal(temp.intValue());

						return new FloatVal(temp.doubleValue());
					} else {
						if (primaryItemList.size() == 1)
							return val;
						else {
							if (primaryItemList.get(primaryItemList.size() - 1).isMul()) {
								System.out.println("Error: * operator cannot be applied to " + val);
							} else {
								System.out.println("Error: / operator cannot be applied to " + val);
							}
							return null;
						}
					}
				}
				return null;
			}
			return null;
		} catch (Exception e) {
			return null;
		}

	}
}