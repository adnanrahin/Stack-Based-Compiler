package interpreter_assignment_103;
import java.util.*;

class NegPrimary extends Primary
{
	Primary primary;

	NegPrimary(Primary p)
	{
		primary = p;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent+" ";

		super.printParseTree(indent);
		IO.displayln("");
		IO.displayln(indent1 + indent1.length() + " -");
		primary.printParseTree(indent1);
	}

	@Override
	Val Eval(Hashtable<String, Val> state) {
		Val val = primary.Eval(state);
		
		if (val == null)
			return null;
		
		if (val instanceof BoolVal)
			return null;
		
		Double value = val.floatVal();
		
		if (val instanceof IntVal)
			return new IntVal(-value.intValue());
		
		return new FloatVal(-value.floatValue());
	}
}