package AST;

public class AST_VARDEC_TYPE_AND_ID_NEW_EXP extends AST_VARDEC
{
    public AST_TYPE_AND_ID typAndId;
    public AST_NEW_EXP nex;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_VARDEC_TYPE_AND_ID_NEW_EXP(AST_TYPE_AND_ID typAndId, AST_NEW_EXP nex)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== varDec -> typeID ASSIGN newExp SEMICOLON");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.typAndId = typAndId;
        this.nex = nex;
    }

    /***************************************************/
    /* The printing message for a variable declaration with type, ID, and new expression AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = VARDEC TYPE AND ID NEW EXP */
        /*********************************/
        System.out.println("AST NODE VARDEC TYPE AND ID NEW EXP");

        /******************************************/
        /* RECURSIVELY PRINT typAndId and nex ... */
        /******************************************/
        if (typAndId != null) typAndId.PrintMe();
        if (nex != null) nex.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "VARDEC\nTYPE\nAND\nID\nNEW EXP");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (typAndId != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, typAndId.SerialNumber);
        if (nex != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, nex.SerialNumber);
    }
}
