package interpreter_assignment_103;

import java.util.*;

public class HeaderWithoutParameters extends Header {
	// FunName funName; inherited from Header

	HeaderWithoutParameters(FunName fName) {
		funName = fName;
	}

	void printParseTree(String indent) {
		super.printParseTree(indent);
	}
}