package java_parser_implementation;

public class Ge extends CompBoolPrimary {
	Ge(E e_1, E e_2) {
		super(e_1, e_2);
	}

	String getOp() {
		return " >=";
	}
}
