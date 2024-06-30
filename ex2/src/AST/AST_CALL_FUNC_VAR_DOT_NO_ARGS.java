package AST;

public class AST_CALL_FUNC_VAR_DOT_NO_ARGS extends AST_CALL_FUNC
{
    public AST_VAR_AND_DOT varDot;
    public String name;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_CALL_FUNC_VAR_DOT_NO_ARGS(AST_VAR_AND_DOT varDot, String name)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.format("====================== callFunc -> varDot ID LPAREN RPAREN\n");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.varDot = varDot;
        this.name = name;
    }

    /***************************************************/
    /* The printing message for a function call with variable and dot without arguments AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = FUNCTION CALL WITH VAR AND DOT WITHOUT ARGUMENTS */
        /*********************************/
        System.out.println("AST NODE FUNCTION CALL WITH VAR AND DOT WITHOUT ARGUMENTS");

        /******************************************/
        /* RECURSIVELY PRINT varDot and name ... */
        /******************************************/
        if (varDot != null) varDot.PrintMe();
        System.out.println("Function Name: " + name);

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "FUNCTION CALL WITH VAR AND DOT WITHOUT ARGUMENTS\nFunction Name: " + name);

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (varDot != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, varDot.SerialNumber);
    }
}
