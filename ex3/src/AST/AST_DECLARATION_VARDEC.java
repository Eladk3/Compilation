package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_DECLARATION_VARDEC extends AST_DECLARATION
{
    public AST_VARDEC varDec;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_DECLARATION_VARDEC(AST_VARDEC varDec, int lineNum)
    {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== dec -> varDec");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.varDec = varDec;
    }

    /***************************************************/
    /* The printing message for a var declaration AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = VAR DECLARATION */
        /*********************************/
        System.out.println("AST NODE DECLARATION VAR DEC");

        /******************************************/
        /* RECURSIVELY PRINT varDec ... */
        /******************************************/
        if (varDec != null) varDec.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "DECLARATION\nVAR DEC");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (varDec != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, varDec.SerialNumber);
    }

    public TYPE SemantMe() {
        if (varDec != null) {
            /***************************************/
            /* [1] Semantically analyze the variable declaration */
            /***************************************/
            return varDec.SemantMe();
        }
        return null;
    }
    
}
