package AST;

public class AST_DECLARATION_ARRAY_TYPEDEF extends AST_DECLARATION
{
    public AST_ARRAY_TYPEDEF arrayTypedef;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_DECLARATION_ARRAY_TYPEDEF(AST_ARRAY_TYPEDEF arrayTypedef)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== dec -> arrayTypedef");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.arrayTypedef = arrayTypedef;
    }

    /***************************************************/
    /* The printing message for an array typedef declaration AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = ARRAY TYPEDEF DECLARATION */
        /*********************************/
        System.out.println("AST NODE ARRAY TYPEDEF DECLARATION");

        /******************************************/
        /* RECURSIVELY PRINT arrayTypedef ... */
        /******************************************/
        if (arrayTypedef != null) arrayTypedef.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "DECLARATION\nARRAY TYPEDEF");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (arrayTypedef != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, arrayTypedef.SerialNumber);
    }
}
