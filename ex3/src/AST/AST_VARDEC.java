package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public abstract class AST_VARDEC extends AST_Node {
    
    public AST_VARDEC(int lineNum){
        super(lineNum);
    }

    public TYPE SemantMe() {
        return null;
    }
}
