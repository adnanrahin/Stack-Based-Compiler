package interpeter_final;

import java.util.HashMap;

abstract class PrimaryItem {
    Primary primary;

    abstract void printParseTree(String indent);

    abstract Val Eval(HashMap<String, Val> state, Val termVal);
}