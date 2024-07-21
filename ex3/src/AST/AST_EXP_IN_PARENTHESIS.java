package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_EXP_IN_PARENTHESIS extends AST_EXP
{
    public AST_EXP e;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_EXP_IN_PARENTHESIS(AST_EXP e, int lineNum)
    {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== exp -> LPAREN exp RPAREN");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.e = e;
    }

    /***************************************************/
    /* The printing message for an expression in parenthesis AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = EXPRESSION IN PARENTHESIS */
        /*********************************/
        System.out.println("AST NODE EXPRESSION IN PARENTHESIS");

        /******************************************/
        /* RECURSIVELY PRINT e ... */
        /******************************************/
        if (e != null) e.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "EXPRESSION\nIN PARENTHESIS");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (e != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, e.SerialNumber);
    }
    
    public TYPE SemantMe() {
        /***************************************/
        /* [1] Semantically analyze the expression inside the parenthesis */
        /***************************************/
        TYPE exprType = e.SemantMe();
        
        /***************************************/
        /* [2] Return the type of the expression inside the parenthesis */
        /***************************************/
        return exprType;
    }

    @Override
	public boolean isConst() {
		return false;
	}	
    
}
