package AST;

public class AST_TYPE_TYPEVOID extends AST_TYPE
{
    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_TYPE_TYPEVOID()
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== typei -> TYPE_VOID");

    }

    /***************************************************/
    /* The printing message for a TYPE_VOID AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = TYPE_VOID */
        /*********************************/
        System.out.println("AST NODE TYPE_VOID");

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "TYPE\nVOID");

    }
}
