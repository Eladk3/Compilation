package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_EXP_NEGATIVE_INT extends AST_EXP
{
    public int i;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_EXP_NEGATIVE_INT(int i, int lineNum)
    {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== exp -> MINUS INT");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.i = i;
    }

    /***************************************************/
    /* The printing message for a negative integer expression AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = EXPRESSION NEGATIVE INT */
        /*********************************/
        System.out.println("AST NODE EXPRESSION NEGATIVE INT");

        /******************************************/
        /* PRINT i ... */
        /******************************************/
        System.out.format("INTEGER VALUE( %d )\n", i);

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            String.format("EXPRESSION\nNEGATIVE INT\n(%d)",i));

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
    }

    public TYPE SemantMe() {
        /***************************************/
        /* [1] Return the type of a negative integer, which is INT_TYPE */
        /***************************************/
        return TYPE_INT.getInstance();
    }
    @Override
	public boolean isConst() {
		return true;
	}	
}
