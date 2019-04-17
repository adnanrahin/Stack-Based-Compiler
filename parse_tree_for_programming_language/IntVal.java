package parse_tree_for_programming_language;

class IntVal extends Val {
	int val;

	IntVal(int i) {
		val = i;
	}

	public String toString() {
		return val + "";
	}

	Val cloneVal() {
		return new IntVal(val);
	}

	float floatVal() {
		return (float) val;
	}

	boolean isZero() {
		return val == 0;
	}
}