package AST;

public class AST_DECLARATION_CLASS_DEC extends AST_DECLARATION
{
    public AST_CLASSDEC classDec;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_DECLARATION_CLASS_DEC(AST_CLASSDEC classDec)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== dec -> classDec");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.classDec = classDec;
    }

    /***************************************************/
    /* The printing message for a class declaration AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = CLASS DECLARATION */
        /*********************************/
        System.out.println("AST NODE CLASS DECLARATION");

        /******************************************/
        /* RECURSIVELY PRINT classDec ... */
        /******************************************/
        if (classDec != null) classDec.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "DECLARATION\nCLASS DEC");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (classDec != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, classDec.SerialNumber);
    }
}
