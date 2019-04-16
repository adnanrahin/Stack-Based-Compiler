package java_parser_implementation;

public class Lt extends CompBoolPrimary {
	Lt(E e_1, E e_2) {
		super(e_1, e_2);
	}

	String getOp() {
		return " <";
	}
}
