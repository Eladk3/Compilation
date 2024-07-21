package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_STMT_RETURN_WITH_EXP extends AST_STMT
{
    public AST_EXP exp;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_STMT_RETURN_WITH_EXP(AST_EXP exp, int lineNum)
    {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== stmt -> RETURN exp SEMICOLON");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.exp = exp;
    }

    /***************************************************/
    /* The printing message for a return statement with expression AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = STATEMENT RETURN WITH EXPRESSION */
        /*********************************/
        System.out.println("AST NODE STATEMENT RETURN WITH EXPRESSION");

        /******************************************/
        /* RECURSIVELY PRINT exp ... */
        /******************************************/
        if (exp != null) exp.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "STATEMENT\nRETURN WITH EXPRESSION");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (exp != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, exp.SerialNumber);
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
    
        /**************************************************/
        /* [3] Check if the return statement has an expression */
        /**************************************************/
        if (exp == null) {
            /***************************************/
            /* [4] Check if the function returns non-void */
            /***************************************/
            if (!(enclosingFunction.returnType instanceof TYPE_VOID)) {
                System.out.format(">> ERROR:[%d] Return statement with missing expression in non-void function", line);
                printError();
                return null;
            }
    
            return TYPE_VOID.getInstance();
        }
    
        /**************************************************/
        /* [6] Check the expression type */
        /**************************************************/
        TYPE returnedType = exp.SemantMe();
    
        /**************************************************/
        /* [7] Check if the returned type matches the function's return type */
        /**************************************************/
        if (!enclosingFunction.returnType.equals(returnedType)) {
            System.out.println("wow1");
            System.out.format(">> ERROR: [%d] Return type mismatch", line);
            printError();
            return null;
        }
    
        return returnedType;
    }
    
}
