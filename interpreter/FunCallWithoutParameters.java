package interpreter;
import java.util.*;

class FunCallWithoutParameters extends FunCall
{
	// FunName funName; inherited from FunCall

	FunCallWithoutParameters(FunName fName)
	{
		funName = fName;
	}
	
	void printParseTree(String indent)
	{
		super.printParseTree(indent+" ");		
	}

	@Override
	Val Eval(Hashtable<String, Val> state) {
		Id id = funName.id;
		if (id == null)
			return null;
		
		FunDef funDef = Parser.funDeftable.get(id.id);
		if (funDef == null)
			return null;
		
		Body body = funDef.body;  // get the body of main function
		Hashtable<String,Val> newState = new Hashtable<String,Val>();
		body.M(newState);
		
		return newState.get("returnVal");
	}
}