package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public abstract class AST_CLASSDEC extends AST_Node {

    public AST_CLASSDEC(int lineNum) {
        super(lineNum);
    }
    public TYPE SemantMe() {
        return null;
    }
    
}
