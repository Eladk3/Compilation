package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public abstract class AST_VAR extends AST_Node
{
    public AST_VAR(int lineNum){
        super(lineNum);
    }
    
    public TYPE SemantMe() {
        return null;
    }

}
