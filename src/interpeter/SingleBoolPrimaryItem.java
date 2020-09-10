package interpeter;
import java.util.*;

class SingleBoolPrimaryItem extends BoolPrimaryItem

// Represents the first <boolPrimary> in <boolTerm>

{
	// BoolPrimary boolPrimary; inherited from BoolPrimaryItem

	SingleBoolPrimaryItem(BoolPrimary bp)
	{
		boolPrimary = bp;
	}

	void printParseTree(String indent)
	{
		boolPrimary.printParseTree(indent);
	}

	Val Eval(Hashtable<String, Val> state) {
		return boolPrimary.Eval(state);
	}
}