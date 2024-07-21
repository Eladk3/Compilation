package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public abstract class AST_EXP extends AST_Node
{
    public AST_EXP(int lineNum) {
        super(lineNum);
    }
	public TYPE SemantMe() {
        return null;
    }
    public abstract boolean isConst();
}