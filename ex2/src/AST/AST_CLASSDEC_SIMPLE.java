package AST;

public class AST_CLASSDEC_SIMPLE extends AST_CLASSDEC
{
    public String name;
    public AST_CFIELD_LIST body;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_CLASSDEC_SIMPLE(String name, AST_CFIELD_LIST body)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.format("====================== classDec -> CLASS ID( %s ) LBRACE cFieldList RBRACE\n", name);

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.name = name;
        this.body = body;
    }

    /***************************************************/
    /* The printing message for a simple class declaration AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = CLASSDEC SIMPLE */
        /*********************************/
        System.out.println("AST NODE CLASSDEC SIMPLE");

        /******************************************/
        /* PRINT class name and body ... */
        /******************************************/
        System.out.format("CLASS NAME( %s )\n", name);
        if (body != null) body.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            String.format("CLASSDEC\nSIMPLE\n...->%s", name));

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (body != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, body.SerialNumber);
    }
}
