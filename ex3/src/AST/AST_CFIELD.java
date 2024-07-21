package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public abstract class AST_CFIELD extends AST_Node{
    public AST_CFIELD(int lineNum) {
        super(lineNum);
    }
    public TYPE SemantMe() {
        return null;
    }
    
}
