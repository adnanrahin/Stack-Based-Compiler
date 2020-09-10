package interpeter;

import java.util.*;

class ArrayVar extends Var {
    ArrayName arrayName;
    EList eList;

    ArrayVar(ArrayName aName, EList el) {
        arrayName = aName;
        eList = el;
    }

    void printParseTree(String indent) {
        String indent1 = indent + " ";
        String indent2 = indent + "  ";

        super.printParseTree(indent);
        IO.displayln("");
        IO.displayln(indent1 + indent1.length() + " <array var>");
        arrayName.printParseTree(indent2);
        eList.printParseTree(indent2);
    }
}