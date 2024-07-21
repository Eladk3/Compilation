package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public abstract class AST_PROGRAM extends AST_Node {

    public AST_PROGRAM(int lineNum) {
        super(lineNum);
    }

    public TYPE SemantMe() {
        return null;
    }
    
}
