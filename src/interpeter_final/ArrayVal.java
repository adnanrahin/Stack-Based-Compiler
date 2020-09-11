package interpeter_final;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class ArrayVal extends Val {
	LinkedList<Integer> sizeList;
	Val a[]; // 1-dim array storing the array elements in row-major order

	ArrayVal(LinkedList<Integer> sl, Val a_[]) {
		sizeList = sl;
		a = a_;
	}

	public String toString() {
		return "array of size list " + sizeList.toString();
	}

	Val cloneVal() // Don't clone, just return the target array itself.
	{
		return this;
	}

	double floatVal()

	// This is not used by the interpreter. For other purposes, this might return a
	// certain code value, e.g.,
	// a hash code of the array.

	{
		return 0.0;
	}

	boolean isNumber() {
		return false;
	}

	boolean isZero() {
		return false;
	}

	int rowMajor(LinkedList<Integer> list) {
		int total = 0;
		for (int i = 0; i < list.size(); i++) {
			total = total * sizeList.get(i);
			total += i;
		}
		return total;
	}

	Val Eval(LinkedList<Val> arrayVal) {
		try {
			if (arrayVal.size() == sizeList.size()) {
				LinkedList<Integer> list = new LinkedList<Integer>();

				for (int i = 0; i < arrayVal.size(); i++) {

					int size = sizeList.get(i);

					if (arrayVal.get(i) != null) {
						if (arrayVal.get(i) instanceof IntVal) {
							list.add((int) arrayVal.get(i).floatVal());
						}
						if (arrayVal.get(i).floatVal() < 0 || arrayVal.get(i).floatVal() >= size) {
							System.out.println(
									"Error: index value of array test out of range: " + arrayVal.get(i).floatVal());
							return null;
						} else if (arrayVal.get(i) instanceof FloatVal) {
							System.out.println("Error: index expression of array test evaluated to non-integer: "
									+ arrayVal.get(i));
							return null;
						}
					} else {
						return null;
					}
				}

				int index = rowMajor(list);

				if (a[index] == null) {
					System.out.println("element of array test does not have a value");
					return null;
				}
				return a[index].cloneVal();
			}
		} catch (NullPointerException e) {
			return null;
		}
		return null;

	}

	void M(HashMap<LinkedList<Val>, Val> state) {

		if (state.size() == 0)
			return;

		LinkedList<Val> arrayVal;
		Val val;

		for (Map.Entry<LinkedList<Val>, Val> itr : state.entrySet()) {

			arrayVal = itr.getKey();

			val = itr.getValue();

			if (arrayVal.size() == sizeList.size()) {

				LinkedList<Integer> list = new LinkedList<Integer>();

				for (int i = 0; i < arrayVal.size(); i++) {

					int size = sizeList.get(i);

					if (arrayVal.get(i) != null) {
						if (arrayVal.get(i) instanceof IntVal) {
							list.add((int) arrayVal.get(i).floatVal());
						}
						if (arrayVal.get(i).floatVal() < 0 || arrayVal.get(i).floatVal() >= size) {
							System.out.println(
									"Error: index value of array test out of range: " + arrayVal.get(i).floatVal());
							return;
						} else if (arrayVal.get(i) instanceof FloatVal) {
							System.out.println("Error: index expression of array test evaluated to non-integer: "
									+ arrayVal.get(i));
							return;
						}
					} else {
						return;
					}
				}

				int index = rowMajor(list);
				a[index] = val;
			}
		}

		return;
	}
}