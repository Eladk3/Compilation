package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_STMT_CALL_FUNC extends AST_STMT
{
    public AST_CALL_FUNC callFunc;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_STMT_CALL_FUNC(AST_CALL_FUNC callFunc, int lineNum)
    {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== stmt -> callFunc SEMICOLON");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.callFunc = callFunc;
    }

    /***************************************************/
    /* The printing message for a function call statement AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = FUNCTION CALL STATEMENT */
        /*********************************/
        System.out.println("AST NODE FUNCTION CALL STATEMENT");

        /******************************************/
        /* RECURSIVELY PRINT callFunc ... */
        /******************************************/
        if (callFunc != null) callFunc.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "FUNCTION CALL STATEMENT");

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
            return callFunc.SemantMe();

        } else {
            System.out.format(">> ERROR: [%d] Function call expression is null", line);
            printError();
            return null;
        }
    }
    
}
