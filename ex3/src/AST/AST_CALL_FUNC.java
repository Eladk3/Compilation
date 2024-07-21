package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;
//remember var dot dont check if var is class
public abstract class AST_CALL_FUNC extends AST_Node {

    public AST_CALL_FUNC(int lineNum) {
        super(lineNum);
    }
    public TYPE SemantMe(){
        return null;
    }
}
