package interpeter_final;

import java.util.LinkedList;

class FunDefList
{
	LinkedList<FunDef> funDefList;

	FunDefList(LinkedList<FunDef> fdl)
	{
		funDefList = fdl;
	}
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <fun def list>");
		for ( FunDef fd : funDefList )
			fd.printParseTree(indent+" ");
	}
}