package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_CFIELD_VARDEC extends AST_CFIELD
{
    public AST_VARDEC varDec;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_CFIELD_VARDEC(AST_VARDEC varDec, int lineNum)
    {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== cField -> varDec");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.varDec = varDec;
    }

    /***************************************************/
    /* The printing message for a class field variable declaration AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = CLASS FIELD VARIABLE DECLARATION */
        /*********************************/
        System.out.println("AST NODE CLASS FIELD VARIABLE DECLARATION");

        /******************************************/
        /* RECURSIVELY PRINT varDec ... */
        /******************************************/
        if (varDec != null) varDec.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "CLASS FIELD\nVARIABLE DECLARATION");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (varDec != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, varDec.SerialNumber);
    }
    public TYPE SemantMe() {
        /*****************************/
        /* [1] Semant Data Members */
        /*****************************/
        if (varDec != null) {
            return varDec.SemantMe();
        }
        return null;
    }
    
}
