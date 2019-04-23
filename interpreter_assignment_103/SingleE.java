package interpreter_assignment_103;
import java.util.*;

class SingleE extends BoolPrimary
{
	E e;

	SingleE(E e_)
	{
		e = e_;
	}
	
	void printParseTree(String indent)
	{
		super.printParseTree(indent);
		e.printParseTree(indent+" ");
	}

	@Override
	Val Eval(Hashtable<String, Val> state) {
		return e.Eval(state);
	}
}