package AST;

public class AST_VARDEC_TYPE_AND_ID_EXP extends AST_VARDEC
{
    public AST_TYPE_AND_ID typAndId;
    public AST_EXP ex;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_VARDEC_TYPE_AND_ID_EXP(AST_TYPE_AND_ID typAndId, AST_EXP ex)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== varDec -> typeID ASSIGN exp SEMICOLON");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.typAndId = typAndId;
        this.ex = ex;
    }

    /***************************************************/
    /* The printing message for a variable declaration with type, ID, and expression AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = VARDEC TYPE AND ID EXP */
        /*********************************/
        System.out.println("AST NODE VARDEC TYPE AND ID EXP");

        /******************************************/
        /* RECURSIVELY PRINT typAndId and ex ... */
        /******************************************/
        if (typAndId != null) typAndId.PrintMe();
        if (ex != null) ex.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "VARDEC\nTYPE\nAND\nID\nEXP");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (typAndId != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, typAndId.SerialNumber);
        if (ex != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, ex.SerialNumber);
    }
}
