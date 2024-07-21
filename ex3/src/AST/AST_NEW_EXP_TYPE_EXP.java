package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_NEW_EXP_TYPE_EXP extends AST_NEW_EXP
{
    public AST_TYPE type;
    public AST_EXP exp;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_NEW_EXP_TYPE_EXP(AST_TYPE type, AST_EXP exp, int lineNum)
    {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== newExp -> NEW type [exp]");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.type = type;
        this.exp = exp;
    }

    /***************************************************/
    /* The printing message for a new expression with type and expression AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = NEW EXPRESSION WITH TYPE AND EXPRESSION */
        /*********************************/
        System.out.println("AST NODE NEW EXPRESSION WITH TYPE AND EXPRESSION");

        /******************************************/
        /* RECURSIVELY PRINT type and exp ... */
        /******************************************/
        if (type != null) type.PrintMe();
        if (exp != null) exp.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "NEW EXPRESSION\nWITH TYPE AND EXPRESSION");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (type != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, type.SerialNumber);
        if (exp != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, exp.SerialNumber);
    }

    public TYPE SemantMe() {
        /***********************************************/
        /* [1] Semantically analyze the type */
        /***********************************************/
        TYPE newType = type.SemantMe();
    
        /***********************************************/
        /* [2] Semantically analyze the size expression (exp) */
        /***********************************************/
        TYPE sizeType = exp.SemantMe();
        
        if (newType instanceof TYPE_VOID) {
            System.out.format(">> ERROR: [%d] new can't be void\n",line);
            printError();
        }

        /*******************************************************/
        /* [3] Make sure the size expression evaluates to an integer */
        /*******************************************************/
        if (!(sizeType instanceof TYPE_INT)) {
            System.out.format(">> ERROR:[%d] Size expression in array creation must evaluate to an integer.", line);
            printError();
        }

        if( exp instanceof AST_EXP_INT && ((AST_EXP_INT)exp).value <= 0){
			System.out.format(">> ERROR [%d] subscript %d must be non negative\n", line, ((AST_EXP_INT)exp).value);
            printError();
		}
    
        /**************************************************/
        /* [4] Return the type of the new expression */
        /**************************************************/
        return newType;
    }
    
}
