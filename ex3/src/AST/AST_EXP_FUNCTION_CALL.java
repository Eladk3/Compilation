package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_EXP_FUNCTION_CALL extends AST_EXP
{
    public AST_CALL_FUNC callFunc;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_EXP_FUNCTION_CALL(AST_CALL_FUNC callFunc, int lineNum)
    {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== exp -> callFunc");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.callFunc = callFunc;
    }

    /***************************************************/
    /* The printing message for a function call expression AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = EXPRESSION FUNCTION CALL */
        /*********************************/
        System.out.println("AST NODE EXPRESSION FUNCTION CALL");

        /******************************************/
        /* RECURSIVELY PRINT callFunc ... */
        /******************************************/
        if (callFunc != null) callFunc.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "EXPRESSION\nFUNCTION CALL");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (callFunc != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, callFunc.SerialNumber);
    }

    public TYPE SemantMe() {
        if (callFunc != null) {
            /***************************************/
            /* [1] Semantically analyze the function call */
            /***************************************/
            TYPE returnType = callFunc.SemantMe();
            
            /**************************************************/
            /* [2] Return the type of the function call */
            /**************************************************/
            return returnType;
        } else {
            System.out.format(">> ERROR:[$d] Function call expression is null", line);
            printError();
            return null;
        }
    }

    @Override
	public boolean isConst() {
		return false;
	}	
    
}
