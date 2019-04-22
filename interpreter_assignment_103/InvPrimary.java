package interpreter_assignment_103;

class InvPrimary extends Primary
{
	Primary primary;

	InvPrimary(Primary p)
	{
		primary = p;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent+" ";

		super.printParseTree(indent);
		IO.displayln("");
		IO.displayln(indent1 + indent1.length() + " !");
		primary.printParseTree(indent1);
	}
}