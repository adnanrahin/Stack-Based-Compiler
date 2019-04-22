package interpreter_assignment_103;

public class Gt extends CompBoolPrimary {
	Gt(E e_1, E e_2) {
		super(e_1, e_2);
	}

	String getOp() {
		return " >";
	}
}
