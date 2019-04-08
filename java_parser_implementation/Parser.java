package java_parser_implementation;
/**

This class is a top-down, recursive-descent parser for the following syntactic categories:

⟨fun def list⟩ → { ⟨fun def⟩ }+ 
⟨fun def⟩ → ⟨header⟩ ⟨body⟩ 
⟨header⟩ → ⟨fun name⟩ "(" [ ⟨parameter list⟩ ] ")" 
⟨fun name⟩ → ⟨id⟩ 
⟨parameter list⟩ → ⟨parameter⟩ { "," ⟨parameter⟩ } 
⟨parameter⟩ → ⟨id⟩ 
⟨body⟩ → "{" ⟨s list⟩ "}" 
⟨s list⟩ → { ⟨statement⟩ } 
⟨statement⟩ → ⟨assignment⟩ | ⟨cond⟩ | ⟨while⟩ | ⟨block⟩ | ⟨fun call statement⟩ | ⟨print⟩ 
⟨assignment⟩ → ⟨var⟩ "=" ⟨right side⟩ ";" 
⟨var⟩ → ⟨id var⟩ | ⟨array var⟩ | "returnVal" 
⟨id var⟩ → ⟨id⟩ 
⟨array var⟩ → ⟨array name⟩ "[" ⟨E list⟩ "]" 
⟨array name⟩ → ⟨id⟩ 
⟨E list⟩ → ⟨E⟩ { "," ⟨E⟩ } 
⟨right side⟩ → ⟨array constructor⟩ | ⟨expr right side⟩ 
⟨array constructor⟩ → "new" "[" ⟨E list⟩ "]" 
⟨expr right side⟩ → ⟨expr⟩ 
⟨cond⟩ → "if" "(" ⟨expr⟩ ")" ⟨statement⟩ [ "else" ⟨statement⟩ ] 
⟨while⟩ → "while" "(" ⟨expr⟩ ")" ⟨statement⟩ 
⟨block⟩ → "{" ⟨s list⟩ "}" 
⟨fun call statement⟩ → ⟨fun call⟩ ";" 
⟨fun call⟩ → ⟨fun name⟩ "(" [ ⟨expr list⟩ ] ")" 
⟨expr list⟩ → ⟨expr⟩ { "," ⟨expr⟩ } 
⟨print⟩ → "print" ⟨expr⟩ ";" 
⟨expr⟩ → ⟨boolTerm⟩ { || ⟨boolTerm⟩ } 
⟨boolTerm⟩ → ⟨boolPrimary⟩ { && ⟨boolPrimary⟩ } 
⟨boolPrimary⟩ → ⟨E⟩ [ ⟨comp op⟩ ⟨E⟩ ] 
⟨comp op⟩ → "<" | "<=" | ">" | ">=" | "==" | "!=" 
⟨E⟩ → ⟨term⟩ { (+|−) ⟨term⟩ } 
⟨term⟩ → ⟨primary⟩ { (*|/) ⟨primary⟩ } 
⟨primary⟩ → ⟨var primary⟩ | ⟨int⟩ | ⟨float⟩ | ⟨floatE⟩ | "(" ⟨expr⟩ ")" | − ⟨primary⟩ | ! ⟨primary⟩ | ⟨fun call primary⟩ 
⟨var primary⟩ → ⟨var⟩ 
⟨fun call primary⟩ → ⟨fun call⟩ 

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
			getToken();
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
		getToken();
		FunName funName = funnName();
		getToken();

		if (state == State.LParen) {
			getToken();
			ParameterList list = parameterList();
			if (state == State.RParen) {
				getToken();
				return new Header(funName, list);
			}
		}
		return null;

	}

	public static Body body() {

		// ⟨body⟩ → "{" ⟨s list⟩ "}"

		if (state == State.LBrace) {
			getToken();
			SList slist = SList();
			if (state == State.RBrace) {
				getToken();
				return new Body(slist);
			} else
				errorMsg(7);
		} else
			errorMsg(6);
		return null;
	}

	public static FunName funnName() {
		return new FunName(t);
	}

	public static ParameterList parameterList() {

		// ⟨parameter list⟩ → ⟨parameter⟩ { "," ⟨parameter⟩ }

		LinkedList<Parameter> parametstLinkedList = new LinkedList<Parameter>();

		Parameter parameter = parameter();
		parametstLinkedList.add(parameter);

		while (state == State.Comma) {
			getToken();
			parameter = parameter();
			parametstLinkedList.add(parameter);
		}

		return new ParameterList(parametstLinkedList);
	}

	public static Parameter parameter() {

		// ⟨parameter⟩ → ⟨id⟩

		if (state == State.Id) {
			String idString = t;
			getToken();
			return new Parameter(idString);
		} else
			return null;
	}

	public static Block block() {

		// ⟨block⟩ → "{" ⟨s list⟩ "}"

		if (state == State.LBrace) {
			getToken();
			SList slist = SList();
			if (state == State.RBrace) {
				getToken();
				return new Block(slist);
			} else
				errorMsg(7);
		} else
			errorMsg(6);
		return null;
	}

	public static SList SList() {

		// ⟨s list⟩ → { ⟨statement⟩ }

		LinkedList<Statement> sList = new LinkedList<Statement>();

		Statement statement;

		while (state == State.Id || state == State.Keyword_if || state == State.Keyword_while
				|| state == State.Keyword_print || state == State.LBrace) {
			statement = statement();
			sList.add(statement);
		}

		return new SList(sList);

	}

	public static Statement statement() {

		// ⟨statement⟩ → ⟨assignment⟩ | ⟨cond⟩ | ⟨while⟩ | ⟨block⟩ |
		// ⟨fun call statement⟩ | ⟨print⟩

		// System.out.println(t);

		if (state == State.Keyword_if) {
			Cond cond = cond();
			return new Statement(cond);
		}

		else if (state == State.Keyword_print) {
			Print print = print();
			return new Statement(print);
		}

		else if (state == State.LBrace) {
			Block block = block();
			return new Statement(block);
		}

		else if (state == State.Keyword_while) {
			While while1 = While();
			return new Statement(while1);
		}

		else if (state == State.Id || state == State.Int || state == State.Float || state == State.FloatE
				|| state == State.Eq) {
			System.out.println(t);
			
			Assignment assignment = assignment();
			return new Statement(assignment);
		} else {
			FunCallStatement funCallStatement = funCallStatement();
			return new Statement(funCallStatement);
		}

	}

	public static Assignment assignment() {

		// ⟨assignment⟩ → ⟨var⟩ "=" ⟨right side⟩ ";"

		if (state == State.Id) {
			Var var = var();
			getToken();
			if (state == State.Assign) {
				getToken();
				RightSide rightSide = rightSide();
				if (state == State.Semicolon) {
					getToken();
					return new Assignment(var, rightSide);
				} else
					errorMsg(4);
			} else
				errorMsg(3);
		} else
			errorMsg(5);
		return null;
	}

	public static Var var() {

		// ⟨var⟩ → ⟨id var⟩ | ⟨array var⟩ | "returnVal"

		String opString = t;

		switch (state) {

		case Id:
			// getToken();
			if (state == State.Assign) {
				getToken();
				if (state == State.Keyword_new) {
					ArrayVar arrayVar = arrayVar();
					return arrayVar;
				} else {
					t = opString;
					IdVar idVar = idVar();
					return idVar;
				}
			}

		case Keyword_returnVal:
			// getToken();
			if (state == State.Eq) {
				return new returnVal(t);
			}
		default:
			errorMsg(2);
			return null;
		}
	}

	public static RightSide rightSide() {

		// ⟨right side⟩ → ⟨array constructor⟩ | ⟨expr right side⟩
		// getToken();
		switch (state) {
		case Keyword_new:
			ArrayConstructor arrayConstructor = arrayConstructor();
			return arrayConstructor;
		default:
			ExprRightSide exprRightSide = exprRightSide();
			return exprRightSide;
		}
	}

	public static ExprRightSide exprRightSide() {

		// ⟨expr right side⟩ → ⟨expr⟩

		Expr expr = expr();

		return new ExprRightSide(expr);

	}

	public static Expr expr() {

		// ⟨expr⟩ → ⟨boolTerm⟩ { || ⟨boolTerm⟩ }

		LinkedList<BoolTerm> booltemrslist = new LinkedList<BoolTerm>();

		BoolTerm boolTerm = boolTerm();

		booltemrslist.add(boolTerm);
		getToken();
		while (state == State.Or) {
			boolTerm = boolTerm();
			booltemrslist.add(boolTerm);
			getToken();
		}

		return new Expr(booltemrslist);

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

		// ⟨primary⟩ → ⟨var primary⟩ | ⟨int⟩ | ⟨float⟩ | ⟨floatE⟩ | "(" ⟨expr⟩ ")" | -⟨primary⟩ | ! ⟨primary⟩ | ⟨fun call primary⟩

		System.out.println("Token : " + t);

		switch (state) {

		case Id:
			// System.out.println(t);
			// System.out.println(" Test " + t);
			getToken();
			VarPrimary varPrimary = varPrimary();
			return varPrimary;

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
			Expr e = expr();
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

	public static VarPrimary varPrimary() {

		// ⟨var primary⟩ → ⟨var⟩
		System.out.println(t + " varfield");
		Var var = var();

		return new VarPrimary(var);

	}

	public static IdVar idVar() {
		// ⟨id var⟩ → ⟨id⟩

		if (state == State.Id) {
			String opString = t;
			getToken();
			return new IdVar(opString);
		} else
			return null;
	}

	public static While While() {

		// ⟨while⟩ → "while" "(" ⟨expr⟩ ")" ⟨statement⟩

		if (state == State.Keyword_while) {
			getToken();
			if (state == State.LParen) {
				getToken();
				Expr expr = expr();
				if (state == State.RParen) {
					getToken();
					Statement statement = statement();
					return new While(expr, statement);
				} else
					errorMsg(6);
			} else
				errorMsg(1);
		} else
			errorMsg(2);
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

	public static CompOp compOp() {

		// ⟨comp op⟩ → "<" | "<=" | ">" | ">=" | "==" | "!="
		if (state == State.Lt || state == State.Le || state == State.Gt || state == State.Ge || state == State.Eq
				|| state == State.Neq)
			return new CompOp(t);
		else {
			displayln(t + " Expected");
			return null;
		}

	}

	public static FunCallPrimary funCallPrimary() {

		// ⟨fun call primary⟩ → ⟨fun call⟩

		FunCall funCall = funCall();

		return new FunCallPrimary(funCall);
	}

	public static Cond cond() {

		// ⟨cond⟩ → "if" "(" ⟨expr⟩ ")" ⟨statement⟩ [ "else" ⟨statement⟩ ]

		if (state == State.Keyword_if) {
			getToken();
			if (state == State.LParen) {
				getToken();
				Expr expr = expr();
				if (state == State.RParen) {
					getToken();
					Statement statement = statement();
					if (state == State.Keyword_else) {
						statement = statement();
						return new Cond(expr, statement);
					}
					return new Cond(expr, statement);
				} else
					errorMsg(1);
			} else
				errorMsg(2);
		} else
			errorMsg(8);
		return null;
	}

	public static Print print() {
		// ⟨print⟩ → "print" ⟨expr⟩ ";"

		if (state == State.Keyword_print) {
			getToken();
			Expr expr = expr();
			if (state == State.Semicolon) {
				getToken();
				return new Print(expr);
			} else
				errorMsg(4);
		} else
			errorMsg(8);
		return null;
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
