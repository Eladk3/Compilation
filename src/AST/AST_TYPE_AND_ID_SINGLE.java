package AST;

public class AST_TYPE_AND_ID_SINGLE extends AST_TYPE_AND_ID
{
    public AST_TYPE type;
    public String name;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_TYPE_AND_ID_SINGLE(AST_TYPE type, String name)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== typeID -> type ID");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.type = type;
        this.name = name;
    }

    /***************************************************/
    /* The printing message for a type and identifier pair AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = TYPE AND IDENTIFIER SINGLE */
        /*********************************/
        System.out.println("AST NODE TYPE AND IDENTIFIER SINGLE");

        /******************************************/
        /* RECURSIVELY PRINT type and name ... */
        /******************************************/
        if (type != null) type.PrintMe();
        System.out.format("IDENTIFIER( %s )\n", name);

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "TYPE AND\nIDENTIFIER SINGLE");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (type != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, type.SerialNumber);
    }
}
