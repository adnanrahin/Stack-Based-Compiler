package assignment_2;
/**

This class is a top-down, recursive-descent parser for the following syntactic categories:

<assignment list> --> { <assignment> }+
<assignment> --> <id> = <E> ";"
<E> --> <term> { (+|-) <term> }
<term> --> <primary> { (*|/) <primary> }
<primary> --> <id> | <int> | <float> | <floatE> | "(" <E> ")"

Note: The binary operators +, -, *, / associate to left.

The definitions of the tokens are given in the lexical analyzer class file "LexArithArray.java". 

The following variables/functions of "IO"/"LexArithArray" classes are used:

static void display(String s)
static void displayln(String s)
static void setIO(String inFile, String outFile)
static void closeIO()

static void setLex()
static String t // holds an extracted token
static State state // the current state of the finite automaton
static int getToken() // extracts the next token

An explicit parse tree is constructed in the form of nested class objects.
 
The main function will display the parse tree in linearly indented form.
Each syntactic category name labeling a node is displayed on a separate line, 
prefixed with the integer i representing the node's depth and indented by i blanks.

All iterations of the form { ... } and { ... }+ are implemented by java.util.LinkedList.
Function call list.add(x) appends element x to the end of list. 

**/

import java.util.*;

public abstract class Parser extends LexArithArray {
	static boolean errorFound = false;

	public static AssignmentList assignmentList()

	// <assignment list> --> { <assignment> }+

	{
		LinkedList<Assignment> assignmentList = new LinkedList<Assignment>();

		Assignment assignment = assignment();
		assignmentList.add(assignment);
		while (state == State.Id) {
			assignment = assignment();
			assignmentList.add(assignment); // append "assignment" to the end of "assignmentList"
		}
		return new AssignmentList(assignmentList);
	}

	public static void block() {
		getToken();
		SList();

		if (state == State.RBrace) {
			getToken();
		} else
			System.out.println("Error: } Expected");
	}

	public static void SList() {
		statement();
		while (state == state.Id || state == state.LBrace) {
			statement();
		}

	}

	public static void statement() {

		if (state == state.Id)
			assignment();

		else if (state == state.Keyword_if || state == state.Keyword_else)
			cond();

		else if (state == state.Keyword_print)
			print();

		else if (state == state.LBrace)
			block();
		
		else if(state == state.Keyword_while)
			While();

		else 
			System.out.println("Error: <Id> or <while> or { or <cond> or <']>");
		
	}

	public static void While() {

	}

	public static void cond() {

	}

	public static void funcallstatement() {

	}

	public static void print() {

	}

	public static Assignment assignment()

	// <assignment> --> <id> = <E> ";"

	{
		if (state == State.Id) {
			String id = t;
			getToken();
			if (state == State.Assign) {
				getToken();
				E e = E();
				if (state == State.Semicolon) {
					getToken();
					return new Assignment(id, e);
				} else
					errorMsg(4);
			} else
				errorMsg(3);
		} else
			errorMsg(5);
		return null;
	}

	public static E E()

	// <E> --> <term> { (+|-) <term> }

	{
		LinkedList<TermItem> termItemList = new LinkedList<TermItem>();

		Term term = term();
		termItemList.add(new SingleTermItem(term));
		while (state == State.Plus | state == State.Minus) {
			State op = state;
			getToken();
			term = term();
			if (op == State.Plus)
				termItemList.add(new AddTermItem(term));
			else // op == State.Minus
				termItemList.add(new SubTermItem(term));
		}
		return new E(termItemList);
	}

	public static Term term()

	// <term> --> <primary> { (*|/) <primary> }

	{
		LinkedList<PrimaryItem> primaryItemList = new LinkedList<PrimaryItem>();

		Primary primary = primary();
		primaryItemList.add(new SinglePrimaryItem(primary));
		while (state == State.Times | state == State.Div) {
			State op = state;
			getToken();
			primary = primary();
			if (op == State.Times)
				primaryItemList.add(new MulPrimaryItem(primary));
			else // op == State.Div
				primaryItemList.add(new DivPrimaryItem(primary));
		}
		return new Term(primaryItemList);
	}

	public static Primary primary()

	// <primary> --> <id> | <int> | <float> | <floatE> | "(" <E> ")"

	{
		switch (state) {
		case Id:

			Id id = new Id(t);
			getToken();
			return id;

		case Int:

			Int intElem = new Int(Integer.parseInt(t));
			getToken();
			return intElem;

		case Float:
		case FloatE:

			Floatp floatElem = new Floatp(Float.parseFloat(t));
			getToken();
			return floatElem;

		case LParen:

			getToken();
			E e = E();
			if (state == State.RParen) {
				getToken();
				Parenthesized paren = new Parenthesized(e);
				return paren;
			} else {
				errorMsg(1);
				return null;
			}

		case Minus:
			getToken();
			Primary primary = primary();
			return primary;
		case Inv:
			getToken();
			Primary primary1 = primary();
			return primary1;
		default:

			errorMsg(2);
			return null;
		}
	}

	public static void errorMsg(int i) {
		errorFound = true;

		display(t + " : Syntax Error, unexpected symbol where");

		switch (i) {
		case 1:
			displayln(" arith op or ) expected");
			return;
		case 2:
			displayln(" id, int, float, or ( expected");
			return;
		case 3:
			displayln(" = expected");
			return;
		case 4:
			displayln(" ; expected");
			return;
		case 5:
			displayln(" id expected");
			return;
		}
	}

	public static void main(String argv[]) {
		// argv[0]: input file containing an assignment list
		// argv[1]: output file displaying the parse tree

		setIO(argv[0], argv[1]);
		setLex();

		getToken();
		
		AssignmentList assignmentList = assignmentList(); // build a parse tree
		if (!t.isEmpty())
			errorMsg(5);
		else if (!errorFound)
			assignmentList.printParseTree(""); // print the parse tree in linearly indented form in argv[1] file

		closeIO();
	}
}
