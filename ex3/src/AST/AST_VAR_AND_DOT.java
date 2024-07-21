package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public abstract class AST_VAR_AND_DOT extends AST_Node{

    public AST_VAR_AND_DOT(int lineNum) {
        super(lineNum);
    }

    public TYPE SemantMe() {
        return null;
    }
}
