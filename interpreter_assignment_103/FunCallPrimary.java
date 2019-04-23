package interpreter_assignment_103;
import java.util.*;

class FunCallPrimary extends Primary
{
	FunCall funCall;

	FunCallPrimary(FunCall fCall)
	{
		funCall = fCall;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent+" ";

		super.printParseTree(indent);
		IO.displayln("");
		IO.displayln(indent1 + indent1.length() + " <fun call primary>");
		funCall.printParseTree(indent1);
	}

	@Override
	Val Eval(Hashtable<String, Val> state) {
		return funCall.Eval(state);
	}
}