package interpeter_final;

import java.util.HashMap;

abstract class BoolTermItem {
    BoolTerm boolTerm;

    abstract void printParseTree(String indent);

    abstract Val Eval(HashMap<String, Val> state, Val exprVal);
}
