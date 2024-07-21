package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public abstract class AST_FUNCDEC extends AST_Node {

    public AST_FUNCDEC(int lineNum) {
        super(lineNum);
    }
    
    public TYPE SemantMe() {
        return null;
    }
    
}
