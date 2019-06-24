package interpreter;

import java.util.*;

class AddTermItem extends TermItem


{
	// Term term; inherited from TermItem

	AddTermItem(Term t) {
		term = t;
	}

	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " +");
		term.printParseTree(indent);
	}

	Val Eval(Hashtable<String, Val> state) {
		Val eVal = term.Eval(state);
		if (eVal instanceof BoolVal) {
			System.out.println("Error: + operator cannot be applied to " + eVal);
			return null;
		}
		return eVal;
	}
	// TODO: Error Handle
	/**Val Eval(HashMap<String,Val> state, Val eVal)
	{
		Val termVal = term.Eval(state);

		if ( eVal == null || termVal == null )
			return null;
		
		// The result will be float if one or both of the arguments are float.
		
		Class    eClass =    eVal.getClass();
		Class termClass = termVal.getClass();
		
		if ( eClass == IntVal.class && termClass == IntVal.class )
		{
			((IntVal)eVal).val = ((IntVal)eVal).val + ((IntVal)termVal).val;
			return eVal;
		}
		else if ( eClass == IntVal.class ) // termClass == FloatVal.class
		{
			((FloatVal)termVal).val = ((IntVal)eVal).val + ((FloatVal)termVal).val;
			return termVal;
		}
		else // eClass == FloatVal.class
		{
			((FloatVal)eVal).val = eVal.floatVal() + termVal.floatVal();
			return eVal;
		}
	}**/
}