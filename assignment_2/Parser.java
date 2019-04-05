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

	public static AssignmentList assignmentList() {

		// <assignment list> --> { <assignment> }+

		LinkedList<Assignment> assignmentList = new LinkedList<Assignment>();

		Assignment assignment = assignment();
		assignmentList.add(assignment);
		while (state == State.Id) {
			assignment = assignment();
			assignmentList.add(assignment); // append "assignment" to the end of "assignmentList"
		}
		return new AssignmentList(assignmentList);
	}

	public static FunDefList funDefList() {

		// ⟨fun def list⟩ → { ⟨fun def⟩ }+

		LinkedList<FunDef> fundeflist = new LinkedList<FunDef>();
		FunDef funDef = fundef();
		fundeflist.add(funDef);

		while (state == State.Id) {
			funDef = fundef();
			fundeflist.add(funDef);
		}

		return new FunDefList(fundeflist);
	}

	public static FunDef fundef() {

		// ⟨fun def⟩ → ⟨header⟩ ⟨body⟩

		Header header = header();
		Body body = body();

		return new FunDef(header, body);

	}

	public static Header header() {

		// ⟨header⟩ → ⟨fun name⟩ "(" [ ⟨parameter list⟩ ] ")"

		funnName();
		getToken();

		if (state == State.LParen) {
			getToken();

			ParameterList list = parameterList();

			if (state == State.RParen) {
				getToken();
				return new Header(list);
			}

		}

		return null;

	}

	public static FunName funnName() {
		// ⟨fun name⟩ → ⟨id⟩

		if (state == State.Id) {
			getToken();
			return new FunName(t);
		} else
			return null;

	}

	public static ParameterList parameterList() {

		// ⟨parameter list⟩ → ⟨parameter⟩ { "," ⟨parameter⟩ }

		LinkedList<Parameter> parametstLinkedList = new LinkedList<Parameter>();

		Parameter parameter = parameter();
		parametstLinkedList.add(parameter);
		getToken();
		while (state == State.Comma) {
			parameter = parameter();
			parametstLinkedList.add(parameter);
			getToken();
		}

		return new ParameterList(parametstLinkedList);
	}

	public static Block block() {

		// ⟨block⟩ → "{" ⟨s list⟩ "}"

		getToken();
		SList slist = SList();

		if (state == State.RBrace) {
			getToken();
			return new Block(slist);
		} else {
			System.out.println("Error: } Expected");
			return null;
		}
	}

	public static SList SList() {

		// ⟨s list⟩ → { ⟨statement⟩ }

		LinkedList<Statement> statementslist = new LinkedList<Statement>();

		while (state == State.Id || state == State.LBrace || state == State.Keyword_if || state == State.Keyword_else
				|| state == State.Keyword_print || state == State.Keyword_while) {
			Statement statement = statement();
			statementslist.add(statement);
			getToken();
		}

		return new SList(statementslist);

	}

	public static Statement statement() {

		// ⟨statement⟩ → ⟨assignment⟩ | ⟨cond⟩ | ⟨while⟩ | ⟨block⟩ | ⟨fun call
		// statement⟩ | ⟨print⟩

		if (state == State.Keyword_if || state == State.Keyword_else) {
			Cond cond = cond();
			return cond;
		}

		else if (state == State.Keyword_print) {
			Print print = print();
			return print;
		}

		else if (state == State.LBrace) {
			Block block = block();
			return block;
		}

		else if (state == State.Keyword_while) {
			While while1 = While();
			return while1;
		}

		else if (state == State.Id || state == State.Int || state == State.Float || state == State.FloatE) {
			Assignment assignment = assignment();
			return assignment;
		} else {
			FunCallStatement funCallStatement = funCallStatement();
			return funCallStatement;
		}

	}

	public static While While() {

		// ⟨while⟩ → "while" "(" ⟨expr⟩ ")" ⟨statement⟩

		getToken();
		if (state == State.LParen) {
			Expr expr = expr();
			getToken();
			if (state == State.RParen) {
				Statement statement = statement();
				return new While(expr, statement);
			}
		}
		return null;
	}

	public static Parameter parameter() {
		// ⟨parameter⟩ → ⟨id⟩

		if (state == State.Id) {
			getToken();
			return new Parameter(t);
		} else
			return null;
	}

	public static Body body() {
		// ⟨body⟩ → "{" ⟨s list⟩ "}"

		getToken();
		SList slist = SList();

		if (state == State.RBrace) {
			getToken();
			return new Body(slist);
		} else {
			System.out.println("Error: } Expected");
			return null;
		}

	}

	public static Var var() {
		// ⟨var⟩ → ⟨id var⟩ | ⟨array var⟩ | "returnVal"

		switch (state) {

		case Id:
			getToken();
			if (state == State.LBracket) {
				getToken();
				if (state == State.RBracket) {
					ArrayVar arrvar = arrayVar();
					return arrvar;
				}
			} else {
				IdVar idVar = idVar();
				return idVar;
			}

		case Keyword_returnVal:
			return new returnVal(t);
		default:
			errorMsg(2);
			return null;
		}
	}

	public static IdVar idVar() {
		// ⟨id var⟩ → ⟨id⟩
		getToken();
		if (state == State.Id) {
			getToken();
			return new IdVar(t);
		} else
			return null;
	}

	public static ArrayVar arrayVar() {

		// ⟨array var⟩ → ⟨array name⟩ "[" ⟨E list⟩ "]"

		getToken();

		ArrayName arrname = arrayName();

		if (state == State.LBracket) {
			EList eList = eList();
			getToken();
			if (state == State.RBracket) {
				return new ArrayVar(arrname, eList);
			}
		}
		return null;
	}

	public static ArrayName arrayName() {
		// ⟨array name⟩ → ⟨id⟩
		if (state == State.Id) {
			getToken();
			return new ArrayName(t);
		} else
			return null;
	}

	public static EList eList() {

		// ⟨E list⟩ → ⟨E⟩ { "," ⟨E⟩ }

		LinkedList<E> elists = new LinkedList<E>();

		E e = E();

		elists.add(e);
		getToken();

		while (state == State.Comma) {
			e = E();
			elists.add(e);
			getToken();
		}

		return new EList(elists);
	}

	public static RightSide rightSide() {

		// ⟨right side⟩ → ⟨array constructor⟩ | ⟨expr right side⟩

		switch (state) {
		case Keyword_new:
			ArrayConstructor arrayConstructor = arrayConstructor();
			return arrayConstructor;
		default:
			ExprRightSide exprRightSide = exprRightSide();
			return exprRightSide;
		}
	}

	public static ArrayConstructor arrayConstructor() {

		// ⟨array constructor⟩ → "new" "[" ⟨E list⟩ "]"

		getToken();

		if (state == State.LBracket) {
			EList eList = eList();
			getToken();
			if (state == State.RBracket) {
				return new ArrayConstructor(eList);
			} else {
				displayln(" ] expected");
			}
		}

		return null;

	}

	public static ExprRightSide exprRightSide() {

		// ⟨expr right side⟩ → ⟨expr⟩

		Expr expr = expr();

		return new ExprRightSide(expr);

	}

	public static FunCallStatement funCallStatement() {

		// ⟨fun call statement⟩ → ⟨fun call⟩ ";"

		FunCall funCall = funCall();
		getToken();

		if (state == State.Semicolon)
			return new FunCallStatement(funCall);

		else {
			displayln(" ; expected");
			return null;
		}

	}

	public static FunCall funCall() {

		// ⟨fun call⟩ → ⟨fun name⟩ "(" [ ⟨expr list⟩ ] ")"

		FunName funName = funnName();

		getToken();

		if (state == State.LParen) {
			ExprList exprList = exprList();
			getToken();
			if (state == State.RParen) {
				return new FunCall(funName, exprList);
			} else {
				displayln(" ) expected");
			}
		}

		return null;

	}

	public static ExprList exprList() {

		// ⟨expr list⟩ → ⟨expr⟩ { "," ⟨expr⟩ }

		LinkedList<Expr> exprlist = new LinkedList<Expr>();

		Expr expr = expr();

		exprlist.add(expr);
		getToken();

		while (state == State.Comma) {
			expr = expr();
			exprlist.add(expr);
			getToken();
		}

		return null;
	}

	public static Expr expr() {

		// ⟨expr⟩ → ⟨boolTerm⟩ { || ⟨boolTerm⟩ }

		LinkedList<BoolTerm> booltemrslist = new LinkedList<BoolTerm>();

		BoolTerm boolTerm = boolTerm();

		booltemrslist.add(boolTerm);

		while (state == State.Or) {
			boolTerm = boolTerm();
			booltemrslist.add(boolTerm);
			getToken();
		}

		return new Expr(booltemrslist);

	}

	public static BoolTerm boolTerm() {

		// ⟨boolTerm⟩ → ⟨boolPrimary⟩ { && ⟨boolPrimary⟩ }

		LinkedList<BoolPrimary> boolPrimaries = new LinkedList<BoolPrimary>();

		BoolPrimary boolPrimary = boolPrimary();

		boolPrimaries.add(boolPrimary);
		getToken();
		while (state == State.And) {
			boolPrimary = boolPrimary();
			boolPrimaries.add(boolPrimary);
			getToken();
		}

		return new BoolTerm(boolPrimaries);
	}

	public static BoolPrimary boolPrimary() {

		// ⟨boolPrimary⟩ → ⟨E⟩ [ ⟨comp op⟩ ⟨E⟩ ]

		E e = E();
		getToken();
		if (state == State.Lt || state == State.Le || state == State.Gt || state == State.Ge || state == State.Eq
				|| state == State.Neq) {
			CompOp compOp = compOp();
			e = E();

			return new BoolPrimary(e, compOp);
		} else
			return new BoolPrimary(e);
	}

	public static CompOp compOp() {

		// ⟨comp op⟩ → "<" | "<=" | ">" | ">=" | "==" | "!="

		return new CompOp(t);

	}

	public static VarPrimary varPrimary() {
		// ⟨var primary⟩ → ⟨var⟩

		Var var = var();

		return new VarPrimary(var);

	}

	public static FunCallPrimary funCallPrimary() {

		// ⟨fun call primary⟩ → ⟨fun call⟩

		FunCall funCall = funCall();

		return new FunCallPrimary(funCall);
	}

	public static Cond cond() {

		// ⟨cond⟩ → "if" "(" ⟨expr⟩ ")" ⟨statement⟩ [ "else" ⟨statement⟩ ]

		getToken();

		if (state == State.LParen) {
			Expr expr = expr();
			getToken();
			if (state == State.RParen) {
				Statement statement = statement();
				getToken();
				if (state == State.Keyword_else) {
					statement = statement();
					return new Cond(expr, statement);
				}
			}
		}
		return null;
	}

	public static Print print() {
		// ⟨print⟩ → "print" ⟨expr⟩ ";"

		Expr expr = expr();
		getToken();
		if (state == State.Semicolon) {
			return new Print(expr);
		}

		return null;

	}

	public static Assignment assignment() {

		// <assignment> --> <id> = <E> ";"

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

	public static E E() {

		// <E> --> <term> { (+|-) <term> }

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

	public static Term term() {

		// <term> --> <primary> { (*|/) <primary> }

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

	public static Primary primary() {

		// <primary> --> <id> | <int> | <float> | <floatE> | "(" <E> ")"s

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

			Primary primary = primary();
			getToken();
			return primary;

		case Inv:

			Primary primary1 = primary();
			getToken();
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

		// getToken();
		FunDefList funDefList = funDefList();
		// build a parse tree
		/*
		 * if (!t.isEmpty()) errorMsg(5); else if (!errorFound)
		 */
		funDefList.printParseTree(""); // print the parse tree in linearly indented form in argv[1] file

		closeIO();
	}
}
