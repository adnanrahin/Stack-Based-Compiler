package java_parser_implementation;
import java.util.*;

public class FunCallWithoutParameters extends FunCall
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
}