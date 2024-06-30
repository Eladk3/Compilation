package AST;

public class AST_DECLARATION_FUNC_DEC extends AST_DECLARATION
{
    public AST_FUNCDEC funcDec;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_DECLARATION_FUNC_DEC(AST_FUNCDEC funcDec)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== dec -> funcDec");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.funcDec = funcDec;
    }

    /***************************************************/
    /* The printing message for a function declaration AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = FUNCTION DECLARATION */
        /*********************************/
        System.out.println("AST NODE FUNCTION DECLARATION");

        /******************************************/
        /* RECURSIVELY PRINT funcDec ... */
        /******************************************/
        if (funcDec != null) funcDec.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "DECLARATION\nFUNC DEC");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (funcDec != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, funcDec.SerialNumber);
    }
}
