package interpeter;
import java.util.*;

class ArrayConstructor extends RightSide
{
	EList eList;

	ArrayConstructor(EList el)
	{
		eList = el;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent+" ";
		
		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <array constructor>");
		eList.printParseTree(indent1+" ");
	}
}