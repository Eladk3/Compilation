package AST;

public class AST_FUNC_ARGS extends AST_Node {

    public AST_TYPE_AND_ID typeID;
    public AST_FUNC_ARGS funcArgs;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_FUNC_ARGS(AST_TYPE_AND_ID typeID, AST_FUNC_ARGS funcArgs) {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        if(funcArgs != null) System.out.println("====================== funcArgs -> typeID COMMA funcArgs");
        else System.out.println("====================== funcArgs -> typeID");
        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.typeID = typeID;
        this.funcArgs = funcArgs;
    }

    /***************************************************/
    /* The printing message for function arguments AST node */
    /***************************************************/
    public void PrintMe() {
        /*********************************/
        /* AST NODE TYPE = FUNCTION ARGUMENTS */
        /*********************************/
        System.out.println("AST NODE FUNCTION ARGUMENTS");

        /******************************************/
        /* RECURSIVELY PRINT typeID and funcArgs ... */
        /******************************************/
        if (typeID != null) typeID.PrintMe();
        if (funcArgs != null) funcArgs.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "FUNCTION ARGUMENTS");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (typeID != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, typeID.SerialNumber);
        if (funcArgs != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, funcArgs.SerialNumber);
    }
}
