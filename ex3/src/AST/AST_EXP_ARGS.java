package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_EXP_ARGS extends AST_Node
{
    public AST_EXP exp;
    public AST_EXP_ARGS expArgs;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_EXP_ARGS(AST_EXP exp, AST_EXP_ARGS expArgs, int lineNum)
    {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        if(expArgs != null) System.out.println("====================== expArgs -> exp COMMA expArgs\n");
        else System.out.println("====================== expArgs -> exp\n");
        
        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.exp = exp;
        this.expArgs = expArgs;
    }

    /***************************************************/
    /* The printing message for an expression argument AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = EXPRESSION ARGUMENT */
        /*********************************/
        System.out.println("AST NODE EXPRESSION ARGUMENT");

        /******************************************/
        /* RECURSIVELY PRINT exp and expArgs */
        /******************************************/
        if (exp != null) exp.PrintMe();
        if (expArgs != null) expArgs.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "EXPRESSION ARGUMENT");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (exp != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, exp.SerialNumber);
        if (expArgs != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, expArgs.SerialNumber);
    }

    public TYPE_LIST SemantMe() {
        /***************************************/
        /* [1] Semantically analyze the type of the argument */
        /***************************************/
        TYPE argumentType = exp.SemantMe();
        
        if (argumentType instanceof TYPE_VOID){
            System.out.format(">> ERROR [%d] param can't be void.\n", line);
            printError();
        }

        /***************************************/
        /* [2] Semantically analyze the rest of the arguments */
        /***************************************/
        if (expArgs == null)
		{
			return new TYPE_LIST(
				argumentType,
				null);
		}
		else
		{
			return new TYPE_LIST(
				argumentType,
				expArgs.SemantMe());
		}
    }
}
