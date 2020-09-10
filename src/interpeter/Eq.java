package interpeter;

import java.util.*;

class Eq extends CompBoolPrimary {
	Eq(E e_1, E e_2) {
		super(e_1, e_2);
	}

	String getOp() {
		return " ==";
	}

	BoolVal Eval(Hashtable<String, Val> state) {

		if (e1.Eval(state).isNumber() && e2.Eval(state).isNumber()
				|| e1.Eval(state) instanceof BoolVal && e2.Eval(state) instanceof BoolVal) {
			return new BoolVal(e1.Eval(state).floatVal() == e2.Eval(state).floatVal());
		}

		else {
			System.out.println(
					"Error: == operator cannot be applied to + " + "[" + e1.Eval(state) + " ," + e2.Eval(state) + "]");
			return null;

		}
	}
}