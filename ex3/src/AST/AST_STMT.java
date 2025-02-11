package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public abstract class AST_STMT extends AST_Node
{

	public AST_STMT(int lineNum) {
		super(lineNum);
	}
	/*********************************************************/
	/* The default message for an unknown AST statement node */
	/*********************************************************/
	public void PrintMe()
	{
		System.out.print("UNKNOWN AST STATEMENT NODE");
	}

	public TYPE SemantMe() {
		return null;		
    }
}
