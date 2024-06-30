package AST;

public class AST_EXP_POSITIVE_INT extends AST_EXP
{
    public int i;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_EXP_POSITIVE_INT(int i)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.format("====================== exp -> INT( %d )\n", i);

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.i = i;
    }

    /***************************************************/
    /* The printing message for a positive integer expression AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = EXPRESSION POSITIVE INT */
        /*********************************/
        System.out.println("AST NODE EXPRESSION POSITIVE INT");

        /******************************************/
        /* PRINT i ... */
        /******************************************/
        System.out.format("INTEGER VALUE( %d )\n", i);

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            String.format("EXPRESSION\nPOSITIVE INT\n(%d)",i));

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
    }
}
