package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_STMT_VARDEC extends AST_STMT
{
    public AST_VARDEC varDec;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_STMT_VARDEC(AST_VARDEC varDec, int lineNum)
    {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== stmt -> varDec");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.varDec = varDec;
    }

    /***************************************************/
    /* The printing message for a variable declaration statement AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = STATEMENT VARIABLE DECLARATION */
        /*********************************/
        System.out.println("AST NODE STATEMENT VARIABLE DECLARATION");

        /******************************************/
        /* RECURSIVELY PRINT varDec ... */
        /******************************************/
        if (varDec != null) varDec.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "STATEMENT\nVARIABLE DECLARATION");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (varDec != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, varDec.SerialNumber);
    }

    public TYPE SemantMe() {
        /***************************************/
        /* [1] Semant the variable declaration */
        /***************************************/
        return varDec.SemantMe();
    
    }
    
}
