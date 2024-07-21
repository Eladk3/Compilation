package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;


public abstract class AST_ARRAY_TYPEDEF extends AST_Node {
    public AST_ARRAY_TYPEDEF(int lineNum) {
        super(lineNum);
    }
    public TYPE SemantMe() {
        return null;
    }
}
