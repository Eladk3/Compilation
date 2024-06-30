package AST;

public class AST_CLASSDEC_INHERITANCE extends AST_CLASSDEC
{
    public String name;
    public String superClass;
    public AST_CFIELD_LIST body;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_CLASSDEC_INHERITANCE(String name, String superClass, AST_CFIELD_LIST body)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.format("====================== classDec -> CLASS ID( %s ) EXTENDS ID( %s ) LBRACE cFieldList RBRACE\n", name, superClass);

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.name = name;
        this.superClass = superClass;
        this.body = body;
    }

    /***************************************************/
    /* The printing message for a class declaration with inheritance AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = CLASSDEC INHERITANCE */
        /*********************************/
        System.out.println("AST NODE CLASSDEC INHERITANCE");

        /******************************************/
        /* PRINT class name, superclass, and body ... */
        /******************************************/
        System.out.format("CLASS NAME( %s )\n", name);
        System.out.format("SUPERCLASS( %s )\n", superClass);
        if (body != null) body.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            String.format("CLASSDEC\nINHERITANCE\n...->%s", name));

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (body != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, body.SerialNumber);
    }
}
