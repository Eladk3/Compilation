package AST;

public class AST_FUNCDEC_WITH_ARGS extends AST_FUNCDEC
{
    public AST_TYPE_AND_ID typAndId;
    public AST_FUNC_ARGS args;
    public AST_STMT_LIST body;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_FUNCDEC_WITH_ARGS(AST_TYPE_AND_ID typAndId, AST_FUNC_ARGS args, AST_STMT_LIST body)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== funcDec -> typeID LPAREN funcArgs RPAREN LBRACE stmtList RBRACE");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.typAndId = typAndId;
        this.args = args;
        this.body = body;
    }

    /***************************************************/
    /* The printing message for a function declaration with arguments AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = FUNCDEC WITH ARGS */
        /*********************************/
        System.out.println("AST NODE FUNCDEC WITH ARGS");

        /******************************************/
        /* RECURSIVELY PRINT typAndId, args, and body ... */
        /******************************************/
        if (typAndId != null) typAndId.PrintMe();
        if (args != null) args.PrintMe();
        if (body != null) body.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "FUNCDEC\nWITH ARGS");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (typAndId != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, typAndId.SerialNumber);
        if (args != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, args.SerialNumber);
        if (body != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, body.SerialNumber);
    }
}

