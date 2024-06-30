package AST;

public class AST_FUNCDEC_NO_ARGS extends AST_FUNCDEC
{
    public AST_TYPE_AND_ID typAndId;
    public AST_STMT_LIST body;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_FUNCDEC_NO_ARGS(AST_TYPE_AND_ID typAndId, AST_STMT_LIST body)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== funcDec -> typeID LPAREN RPAREN LBRACE stmtList RBRACE");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.typAndId = typAndId;
        this.body = body;
    }

    /***************************************************/
    /* The printing message for a function declaration with no arguments AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = FUNCDEC NO ARGS */
        /*********************************/
        System.out.println("AST NODE FUNCDEC NO ARGS");

        /******************************************/
        /* RECURSIVELY PRINT typAndId and body ... */
        /******************************************/
        if (typAndId != null) typAndId.PrintMe();
        if (body != null) body.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "FUNCDEC\nNO ARGS");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (typAndId != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, typAndId.SerialNumber);
        if (body != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, body.SerialNumber);
    }
}
