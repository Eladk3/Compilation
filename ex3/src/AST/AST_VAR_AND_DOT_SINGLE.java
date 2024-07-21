package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_VAR_AND_DOT_SINGLE extends AST_VAR_AND_DOT
{
    public AST_VAR var;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_VAR_AND_DOT_SINGLE(AST_VAR var, int lineNum)
    {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== varDot -> var DOT");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.var = var;
    }

    /***************************************************/
    /* The printing message for a variable and dot AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = VARIABLE AND DOT SINGLE */
        /*********************************/
        System.out.println("AST NODE VARIABLE AND DOT SINGLE");

        /******************************************/
        /* RECURSIVELY PRINT var ... */
        /******************************************/
        if (var != null) var.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "VARIABLE AND DOT\nSINGLE");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (var != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, var.SerialNumber);
    }

    public TYPE SemantMe() {
        return var.SemantMe();
    }
    
}
