package interpreter_final;

import java.util.*;

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
}