package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_EXP_NIL extends AST_EXP
{

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_EXP_NIL(int lineNum)
    {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== exp -> NIL");

    }

    /***************************************************/
    /* The printing message for a nil expression AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = EXPRESSION NIL */
        /*********************************/
        System.out.println("AST NODE EXPRESSION NIL");

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "EXPRESSION\nNIL");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
    }

    public TYPE SemantMe() {
        /***************************************/
        /* [1] Return the type of nil, which is NIL_TYPE */
        /***************************************/
        return TYPE_NIL.getInstance();
    }
    @Override
	public boolean isConst() {
		return true;
	}	
    
}
