package AST;

public class AST_CALL_FUNC_VAR_DOT_WITH_ARGS extends AST_CALL_FUNC
{
    public AST_VAR_AND_DOT varDot;
    public String name;
    public AST_EXP_ARGS expArgs;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_CALL_FUNC_VAR_DOT_WITH_ARGS(AST_VAR_AND_DOT varDot, String name, AST_EXP_ARGS expArgs)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== callFunc -> varDot ID LPAREN expArgs RPAREN\n");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.varDot = varDot;
        this.name = name;
        this.expArgs = expArgs;
    }

    /***************************************************/
    /* The printing message for a function call with variable and dot and arguments AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = FUNCTION CALL WITH VAR AND DOT AND ARGUMENTS */
        /*********************************/
        System.out.println("AST NODE FUNCTION CALL WITH VAR AND DOT AND ARGUMENTS");

        /******************************************/
        /* RECURSIVELY PRINT varDot and name */
        /******************************************/
        if (varDot != null) varDot.PrintMe();
        System.out.println("Function Name: " + name);

        /******************************************/
        /* RECURSIVELY PRINT expArgs */
        /******************************************/
        if (expArgs != null) expArgs.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "FUNCTION CALL WITH VAR AND DOT AND ARGUMENTS\nFunction Name: " + name);

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (varDot != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, varDot.SerialNumber);
        if (expArgs != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, expArgs.SerialNumber);
    }
}
