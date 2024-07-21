package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_STMT_RETURN extends AST_STMT
{

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_STMT_RETURN(int lineNum)
    {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== stmt -> RETURN SEMICOLON");

    }

    /***************************************************/
    /* The printing message for a return statement AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = STATEMENT RETURN */
        /*********************************/
        System.out.println("AST NODE STATEMENT RETURN");

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "STATEMENT\nRETURN");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
    }

    public TYPE SemantMe() {
        /***************************************/
        /* [1] Check if returning from within a function */
        /***************************************/
        if (!SYMBOL_TABLE.getInstance().isFunctionScope()) {
            System.out.format(">> ERROR:[%d] Return statement outside of function scope", line);
            printError();
            return null;
        }
    
        /***************************************/
        /* [2] Check if the function returns void */
        /***************************************/
        TYPE_FUNCTION enclosingFunction = SYMBOL_TABLE.getInstance().getCurrentFunction();

        if (enclosingFunction.returnType instanceof TYPE_VOID) {
            return TYPE_VOID.getInstance();
        }
    
        System.out.format(">> ERROR:[%d] Return statement should be void", line);
        printError();
        return null;
    }
    
}
