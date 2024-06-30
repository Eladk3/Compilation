package AST;

public class AST_VARDEC_TYPE_AND_ID extends AST_VARDEC
{
    public AST_TYPE_AND_ID typAndId;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_VARDEC_TYPE_AND_ID(AST_TYPE_AND_ID typAndId)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== varDec -> typeID SEMICOLON");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.typAndId = typAndId;
    }

    /***************************************************/
    /* The printing message for a variable declaration with type and ID AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = VARDEC TYPE AND ID */
        /*********************************/
        System.out.println("AST NODE VARDEC TYPE AND ID");

        /******************************************/
        /* RECURSIVELY PRINT typAndId ... */
        /******************************************/
        if (typAndId != null) typAndId.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "VARDEC\nTYPE\nAND\nID");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (typAndId != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, typAndId.SerialNumber);
    }
}
