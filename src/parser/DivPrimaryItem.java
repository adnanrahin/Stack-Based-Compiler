package parser;

import java.util.*;

class DivPrimaryItem extends PrimaryItem {


    // Represents "/ <primary>"
    // Primary primary; inherited from PrimaryItem

    DivPrimaryItem(Primary p) {
        primary = p;
    }

    void printParseTree(String indent) {
        IO.displayln(indent + indent.length() + " /");
        primary.printParseTree(indent);
    }
}
