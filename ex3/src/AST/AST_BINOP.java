package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public abstract class AST_BINOP extends AST_Node {

    public AST_BINOP(int lineNum) {
        super(lineNum);
    }
    public TYPE SemantMe() {
        return null;  
    }
}
