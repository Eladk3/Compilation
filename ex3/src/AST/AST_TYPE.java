package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public abstract class AST_TYPE extends AST_Node {

    public AST_TYPE(int lineNum) {
        super(lineNum);        
    }

    public TYPE SemantMe() {
        return null;
    }
    
}
