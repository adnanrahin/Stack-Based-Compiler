package project_2;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * 
 * This class is a lexical analyzer for the tokens defined by the grammar:
 * 
 * <add> --> + <Sub> --> - <mul> --> * <div> --> / <LParen> --> "(" <RParen> -->
 * ")" <int> --> { <digit> }+ <id> --> <letter> { <letter> | <digit> } <float>
 * --> { <digit> }+ "." { <digit> }+ <floatE> --> <float> (E|e) [+|-] { <digit>
 * }+
 * 
 * This class implements a DFA that will accept the above tokens.
 * 
 * The DFA states are represented by the Enum type "State". The DFA has the
 * following 10 final states represented by enum-type literals:
 * 
 * state token accepted
 * 
 * Id identifiers Int integers Float floats without exponentiation part FloatE
 * floats with exponentiation part add + Sub - mul * Div / LParen ( RParen )
 * 
 * The DFA also uses the following 4 non-final states:
 * 
 * state string recognized
 * 
 * Start the empty string Period float parts ending with "." E float parts
 * ending with E or e EaddSub float parts ending with + or - in exponentiation
 * part
 * 
 * The function "driver" operates the DFA. The function "nextState" returns the
 * next state given the current state and the input character.
 * 
 * To recognize a different token set, modify the following:
 * 
 * enum type "State" and function "isFinal" function "nextState"
 * 
 * The functions "driver", "getToken" remain the same.
 * 
 **/

public abstract class LexArith extends IO {

	public static List<String> globalstatestring = new ArrayList<String>();

	public enum State {

		// non-final state ordinal number

		Start, Period, E, EaddSub,

		Id, Int, Float, FloatE, add, Sub, mul, Div, LParen, RParen, or, and, inv, lt, le, gt, ge, eq, assign, LBrace,
		RBrace, LBracket, RBracket, semicolon, comma, neq, Keyword_else, Keyword_if, Keyword_while, Keyword_returnVal,
		Keyword_new, Keyword_print, UNDEF;

		private boolean isFinal() {
			return (this.compareTo(State.Id) >= 0);
		}
	}

	/*
	 * By enumerating the non-final states first and then the final states, test for
	 * a final state can be done by testing if the state's ordinal number is greater
	 * than or equal to that of Id.
	 * 
	 * The following variables of "IO" class are used:
	 * 
	 * static int a; the current input character static char c; used to convert the
	 * variable "a" to the char type whenever necessary
	 */

	public static String t; // holds an extracted token
	public static State state; // the current state of the FA

	/*
	 * This is the driver of the FA. If a valid token is found, assigns it to "t"
	 * and returns 1. If an invalid token is found, assigns it to "t" and returns 0.
	 * If end-of-stream is reached without finding any non-whitespace character,
	 * returns -1.
	 */

	private static int driver() {

		State nextSt; // the next state of the FA

		t = "";
		state = State.Start;

		if (Character.isWhitespace((char) a))
			a = getChar(); // get the next non-whitespace character
		if (a == -1) // end-of-stream is reached
			return -1;

		while (a != -1) // do the body if "a" is not end-of-stream
		{
			c = (char) a;

			nextSt = nextState(state, c);
			if (nextSt == State.UNDEF) // The FA will halt.
			{
				if (state.isFinal()) {
					globalstatestring.add(t);
					return 1;
				} // valid token extracted
				else // "c" is an unexpected character
				{

					t = t + c;
					a = getNextChar();
					return 0; // invalid token found
				}
			} else // The FA will go on.
			{
				state = nextSt;
				t = t + c;
				a = getNextChar();
			}
		}
		globalstatestring.add(t);
		// end-of-stream is reached while a token is being extracted

		if (state.isFinal())
			return 1; // valid token extracted
		else
			return 0; // invalid token found
	} // end driver

	public static void getToken() {

		int i = driver();
		if (i == 0)
			displayln(t + " : Lexical Error, invalid token");
	}

	private static State nextState(State s, char c) {
		switch (state) {
		case Start:
			if (Character.isLetter(c))
				return State.Id;
			else if (Character.isDigit(c))
				return State.Int;

			else if (c == '+')
				return State.add;

			else if (c == '-')
				return State.Sub;

			else if (c == '*')
				return State.mul;

			else if (c == '/')
				return State.Div;

			else if (c == '(')
				return State.LParen;

			else if (c == ')')
				return State.RParen;

			else if (c == '&')
				return State.and;

			else if (c == '|')
				return State.or;

			else if (c == ';')
				return State.semicolon;

			else if (c == ',')
				return State.comma;

			else if (c == '{')
				return State.LBrace;

			else if (c == '}')
				return State.RBrace;

			else if (c == '[')
				return State.LBracket;

			else if (c == ']')
				return State.RBracket;

			else if (c == '=')
				return State.assign;

			else if (c == '!')
				return State.inv;

			else if (c == '<')
				return State.lt;

			else if (c == '>')
				return State.gt;

			else
				return State.UNDEF;

		case Id:
			if (Character.isLetterOrDigit(c))
				return State.Id;
			else
				return State.UNDEF;
		case Int:
			if (Character.isDigit(c))
				return State.Int;
			else if (c == '.')
				return State.Period;
			else
				return State.UNDEF;
		case Period:
			if (Character.isDigit(c))
				return State.Float;
			else
				return State.UNDEF;
		case Float:
			if (Character.isDigit(c))
				return State.Float;
			else if (c == 'e' || c == 'E')
				return State.E;
			else
				return State.UNDEF;
		case E:
			if (Character.isDigit(c))
				return State.FloatE;
			else if (c == '+' || c == '-')
				return State.EaddSub;
			else
				return State.UNDEF;
		case EaddSub:
			if (Character.isDigit(c))
				return State.FloatE;
			else
				return State.UNDEF;
		case FloatE:
			if (Character.isDigit(c))
				return State.FloatE;
			else
				return State.UNDEF;
		case and:
			if (c == '&')
				return State.and;
			else
				return State.UNDEF;
		case or:
			if (c == '|')
				return State.or;
			else
				return State.UNDEF;
		case assign:
			if (c == '=')
				return State.eq;
			else
				return State.UNDEF;
		case inv:
			if (c == '=')
				return State.neq;
			else
				return State.UNDEF;
		case lt:
			if (c == '=')
				return State.le;
			else
				return State.UNDEF;
		case gt:
			if (c == '=')
				return State.ge;
			else
				return State.UNDEF;
		default:
			return State.UNDEF;
		}
	} // end nextState

	public static Hashtable<String, State> keyWordMap() {

		Hashtable<String, State> StateKeymap = new Hashtable<String, State>();

		StateKeymap.put("while", State.Keyword_while);
		StateKeymap.put("if", State.Keyword_if);
		StateKeymap.put("else", State.Keyword_else);
		StateKeymap.put("new", State.Keyword_new);
		StateKeymap.put("print", State.Keyword_print);
		StateKeymap.put("returnVal", State.Keyword_returnVal);
		StateKeymap.put("|", State.UNDEF);
		StateKeymap.put("&", State.UNDEF);

		return StateKeymap;
	}

	public static void main(String argv[]) {

		// argv[0]: input file containing source code using tokens defined above
		// argv[1]: output file displaying a list of the tokens

		/*
		 * Set the external Arguments for this code, where input file path will go on
		 * top and in the next line output file path
		 */

		Hashtable<String, State> stateKeymap = keyWordMap();

		setIO(argv[0], argv[1]);

		int i;

		while (a != -1) // while "a" is not end-of-stream
		{
			i = driver(); // extract the next token
			if (i == 1)
				if (stateKeymap.containsKey(t)) {
					if (stateKeymap.get(t) == State.UNDEF) {
						displayln(t + " : Lexical Error, invalid token");
					} else
						displayln(t + "  : " + stateKeymap.get(t).toString());
				} else
					displayln(t + "  : " + state.toString());
			else if (i == 0)
				displayln(t + " : Lexical Error, invalid token");
		}
		
		for(String str: globalstatestring)
			System.out.println(str);

		closeIO();
	}
}
