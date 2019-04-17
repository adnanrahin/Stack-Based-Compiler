package parse_tree_for_programming_language;

class FloatVal extends Val {
	float val;

	FloatVal(float f) {
		val = f;
	}

	public String toString() {
		return val + "";
	}

	Val cloneVal() {
		return new FloatVal(val);
	}

	float floatVal() {
		return val;
	}

	boolean isZero() {
		return val == 0.0f;
	}
}