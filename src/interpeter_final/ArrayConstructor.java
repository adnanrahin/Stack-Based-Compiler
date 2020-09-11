package interpeter_final;

import java.util.HashMap;
import java.util.LinkedList;

class ArrayConstructor extends RightSide {
	EList eList;

	ArrayConstructor(EList el) {
		eList = el;
	}

	void printParseTree(String indent) {
		String indent1 = indent + " ";

		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <array constructor>");
		eList.printParseTree(indent1 + " ");
	}

	Val Eval(HashMap<String, Val> state) {
		try {
			LinkedList<Val> elist = new LinkedList<Val>();
			elist = eList.Eval(state);

			LinkedList<Integer> list = new LinkedList<Integer>();
			int total = 1;

			for (int i = 0; i < elist.size(); i++) {

				if (elist.get(i) == null) {

					if (elist.get(i) instanceof FloatVal) {

						if (elist.get(i).floatVal() < 0.0) {
							System.out.println(
									"Error: array size expression evaluated to non-positive integer: " + elist.get(i));
							return null;
						} else {
							System.out
									.println("Error: array size expression evaluated to non-integer: " + elist.get(i));
							return null;
						}
					}
					return null;
				}

				list.add((int) elist.get(i).floatVal());
				total *= (int) elist.get(i).floatVal();
			}

			return new ArrayVal(list, new Val[total]);
		} catch (Exception e) {
			return null;
		}
	}
}