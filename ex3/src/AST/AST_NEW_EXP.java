package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public abstract class AST_NEW_EXP extends AST_Node {

    public AST_NEW_EXP(int lineNum) {
        super(lineNum);
    }
    
    public TYPE SemantMe() {
        return null;
    }
    
}
