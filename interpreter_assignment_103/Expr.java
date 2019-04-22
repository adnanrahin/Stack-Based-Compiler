package interpreter_assignment_103;

import java.util.LinkedList;

public class Expr {

	LinkedList<BoolTermItem> boolTermItemList;

	public Expr(LinkedList<BoolTermItem> btItemList) {
		boolTermItemList = btItemList;
	}

	public void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <expr>");
		for (BoolTermItem bt : boolTermItemList)
			bt.printParseTree(indent + " ");
	}

}
