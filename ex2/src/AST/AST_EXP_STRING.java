package AST;

public class AST_EXP_STRING extends AST_EXP
{
    public String s;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_EXP_STRING(String s)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== exp -> STRING");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.s = s;
    }

    /***************************************************/
    /* The printing message for a string expression AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = EXPRESSION STRING */
        /*********************************/
        System.out.println("AST NODE EXPRESSION STRING");

        /******************************************/
        /* PRINT s ... */
        /******************************************/
        System.out.format("STRING VALUE( %s )\n", s);

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            String.format("EXPRESSION\nSTRING\n(%s)",s));

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
    }
}
