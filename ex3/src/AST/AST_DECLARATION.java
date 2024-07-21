package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public abstract class AST_DECLARATION extends AST_Node {

    public AST_DECLARATION(int lineNum) {
        super(lineNum);
    }
    public TYPE SemantMe() {
        return null;
    }
    
}
