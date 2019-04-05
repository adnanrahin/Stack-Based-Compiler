package hassan_code;

import java.io.BufferedReader;
import java.io.FileReader;

class Lexeme {
	public String token;
	public String category;

	// Create a new lexeme
	public Lexeme(String token, String category) {
		this.token = token;
		this.category = category;
	}

	@Override
	public String toString() {
		if (category != null) {
			return token + "   : " + category;
		}

		return token + " : Lexical Error, invalid token";
	}
}

public class LexParser {

	private BufferedReader br;
	private char currentChar;

	// Create a lex parser that read from a file
	public LexParser(String filename) throws Exception {
		br = new BufferedReader(new FileReader(filename));
		currentChar = '\0';
	}

	// Return the next lexeme
	public Lexeme getNext() throws Exception {
		if (currentChar == '\0' || Character.isWhitespace(currentChar)) {
			// Find the next non-whitespace character
			int data = br.read();

			while (data != -1 && Character.isWhitespace(data)) {
				data = br.read();
			}

			if (data == -1) {
				br.close();
				return null;
			}

			currentChar = (char) data;
		}

		if (currentChar == -1) {
			return null;
		}

		// Depending on the current character, we then
		// find which state we should go into
		String token = "" + currentChar;

		// Check for single character operators
		if (currentChar == '+') {
			Lexeme lexeme = new Lexeme(String.valueOf(currentChar), "Add");
			currentChar = '\0';
			return lexeme;
		}

		if (currentChar == '-') {
			Lexeme lexeme = new Lexeme(String.valueOf(currentChar), "Sub");
			currentChar = '\0';
			return lexeme;
		}

		if (currentChar == '*') {
			Lexeme lexeme = new Lexeme(String.valueOf(currentChar), "Mul");
			currentChar = '\0';
			return lexeme;
		}

		if (currentChar == '/') {
			Lexeme lexeme = new Lexeme(String.valueOf(currentChar), "Div");
			currentChar = '\0';
			return lexeme;
		}

		if (currentChar == '(') {
			Lexeme lexeme = new Lexeme(String.valueOf(currentChar), "LParen");
			currentChar = '\0';
			return lexeme;
		}

		if (currentChar == ')') {
			Lexeme lexeme = new Lexeme(String.valueOf(currentChar), "RParen");
			currentChar = '\0';
			return lexeme;
		}

		if (currentChar == '{') {
			Lexeme lexeme = new Lexeme(String.valueOf(currentChar), "LBrace");
			currentChar = '\0';
			return lexeme;
		}

		if (currentChar == '}') {
			Lexeme lexeme = new Lexeme(String.valueOf(currentChar), "RBrace");
			currentChar = '\0';
			return lexeme;
		}

		if (currentChar == '[') {
			Lexeme lexeme = new Lexeme(String.valueOf(currentChar), "LBracket");
			currentChar = '\0';
			return lexeme;
		}

		if (currentChar == ']') {
			Lexeme lexeme = new Lexeme(String.valueOf(currentChar), "RBracket");
			currentChar = '\0';
			return lexeme;
		}

		if (currentChar == ';') {
			Lexeme lexeme = new Lexeme(String.valueOf(currentChar), "Semicolon");
			currentChar = '\0';
			return lexeme;
		}

		if (currentChar == ',') {
			Lexeme lexeme = new Lexeme(String.valueOf(currentChar), "Comma");
			currentChar = '\0';
			return lexeme;
		}

		// Check for double letter operators
		if (currentChar == '=') {
			int data = br.read();

			if (data != '=') {
				Lexeme lexeme = new Lexeme(String.valueOf(currentChar), "Assign");
				currentChar = (char) data;
				return lexeme;
			}

			token += (char) data;
			Lexeme lexeme = new Lexeme(token, "Eq");
			currentChar = '\0';
			return lexeme;
		}

		if (currentChar == '>') {
			int data = br.read();

			if (data != '=') {
				Lexeme lexeme = new Lexeme(String.valueOf(currentChar), "Gt");
				currentChar = (char) data;
				return lexeme;
			}

			token += (char) data;
			Lexeme lexeme = new Lexeme(token, "Ge");
			currentChar = '\0';
			return lexeme;
		}

		if (currentChar == '<') {
			int data = br.read();

			if (data != '=') {
				Lexeme lexeme = new Lexeme(String.valueOf(currentChar), "Lt");
				currentChar = (char) data;
				return lexeme;
			}

			token += (char) data;
			Lexeme lexeme = new Lexeme(token, "Le");
			currentChar = '\0';
			return lexeme;
		}

		if (currentChar == '!') {
			int nextData = br.read();

			if (nextData != '=') {
				Lexeme lexeme = new Lexeme(String.valueOf(currentChar), "Inv");
				currentChar = (char) nextData;
				return lexeme;
			}

			token += (char) nextData;
			Lexeme lexeme = new Lexeme(token, "Neq");
			currentChar = '\0';
			return lexeme;
		}

		if (currentChar == '|') {
			token += (char) br.read();

			if (token.equals("||")) {
				Lexeme lexeme = new Lexeme(token, "Or");
				currentChar = '\0';
				return lexeme;
			}

			currentChar = '\0';
			return new Lexeme(token, null);
		}

		if (currentChar == '&') {
			token += (char) br.read();

			if (token.equals("&&")) {
				Lexeme lexeme = new Lexeme(token, "And");
				currentChar = '\0';
				return lexeme;
			}

			currentChar = '\0';
			return new Lexeme(token, null);
		}

		// If character starts with a letter then its an identifier or possibly a
		// keyword
		if (Character.toUpperCase(currentChar) >= 'A' && Character.toUpperCase(currentChar) <= 'Z') {
			int nextData = br.read();

			while ((Character.toUpperCase(nextData) >= 'A' && Character.toUpperCase(nextData) <= 'Z')
					|| (nextData >= '0' && nextData <= '9')) {
				token += (char) nextData;
				nextData = br.read();
			}

			currentChar = (char) nextData;

			if (token.equals("if") || token.equals("else") || token.equals("while") || token.equals("returnVal")
					|| token.equals("new") || token.equals("print")) {
				return new Lexeme(token, "Keyword_" + token);
			}

			return new Lexeme(token, "Id");
		}

		// If character starts with a digit then it could be an integer, float, or a
		// float exponent
		if (currentChar >= '0' && currentChar <= '9') {
			int nextData = br.read();

			while (nextData >= '0' && nextData <= '9') {
				token += (char) nextData;
				nextData = br.read();
			}

			if (nextData == '.') {
				// If the last data found is a '.' then its a float
				token += (char) nextData;

				nextData = br.read();
				token += (char) nextData;

				// Next character should be a digit
				if (nextData < '0' || nextData > '9') {
					currentChar = '\0';
					return new Lexeme(token, null);
				}

				// The rest is a digit
				nextData = br.read();

				while (nextData >= '0' && nextData <= '9') {
					token += (char) nextData;
					nextData = br.read();
				}

				// If an 'E' is found then its a floating exponent
				if (nextData == 'e' || nextData == 'E') {
					token += (char) nextData;

					nextData = br.read();
					token += (char) nextData;

					if (nextData == '+' || nextData == '-') {
						nextData = br.read();
						token += (char) nextData;
					}

					// Next character should be a digit
					if (nextData < '0' || nextData > '9') {
						currentChar = '\0';
						return new Lexeme(token, null);
					}

					// The rest are digits
					nextData = br.read();

					while (nextData >= '0' && nextData <= '9') {
						token += (char) nextData;
						nextData = br.read();
					}

					currentChar = (char) nextData;
					return new Lexeme(token, "FloatE");
				} else {
					currentChar = (char) nextData;
					return new Lexeme(token, "Float");
				}
			} else {
				currentChar = (char) nextData;
				return new Lexeme(token, "Int");
			}
		}

		Lexeme lexeme = new Lexeme(token, null);
		currentChar = '\0';
		return lexeme;
	}
}