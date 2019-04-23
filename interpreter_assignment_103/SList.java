package interpreter_assignment_103;
import java.util.*;

class SList
{
	LinkedList<Statement> sList;

	SList(LinkedList<Statement> sl)
	{
		sList = sl;
	}
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <s list>");
		for ( Statement s : sList )
			s.printParseTree(indent+" ");
	}

	void M(Hashtable<String, Val> state) {
		for (Statement statement : sList) {
			statement.M(state);
		}
	}
}