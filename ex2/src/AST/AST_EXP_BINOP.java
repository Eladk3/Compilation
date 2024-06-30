package AST;

public class AST_EXP_BINOP extends AST_EXP
{
    public AST_EXP e1;
    public AST_BINOP op;
    public AST_EXP e2;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_EXP_BINOP(AST_EXP e1, AST_BINOP op, AST_EXP e2)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== exp -> exp BINOP exp");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.e1 = e1;
        this.op = op;
        this.e2 = e2;
    }

    /***************************************************/
    /* The printing message for a binary operation expression AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = EXPRESSION BINOP */
        /*********************************/
        System.out.println("AST NODE EXPRESSION BINOP");

        /******************************************/
        /* RECURSIVELY PRINT e1 and e2 ... */
        /******************************************/
        if (e1 != null) e1.PrintMe();
        if (op != null) op.PrintMe();
        if (e2 != null) e2.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "EXPRESSION\nBINOP");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (e1 != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, e1.SerialNumber);
        if (op != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, op.SerialNumber);
        if (e2 != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, e2.SerialNumber);
    }
}
