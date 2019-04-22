package interpreter_assignment_103;

public abstract class Val {
	abstract Val cloneVal();

	abstract double floatVal();

	abstract boolean isNumber();

	abstract boolean isZero();
}