package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_TYPE_TYPEID extends AST_TYPE
{
    public String typeName;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_TYPE_TYPEID(String typeName, int lineNum)
    {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.format("====================== typei -> ID( %s )\n", typeName);

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.typeName = typeName;
    }

    /***************************************************/
    /* The printing message for a TYPE_ID AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = TYPE_ID */
        /*********************************/
        System.out.println("AST NODE TYPE TYPEID");

        /******************************************/
        /* PRINT type name */
        /******************************************/
        System.out.format("TYPE NAME( %s )\n", typeName);

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            String.format("TYPE\nID\n...->%s", typeName));
    }

    public TYPE SemantMe() {
        TYPE t = SYMBOL_TABLE.getInstance().find(typeName);

        if (t == null) {
            System.out.format(">> ERROR [%d] %s is not defined\n", line, typeName);
            printError();
            return null;
        }

        if (!(t.isArray() || t.isClass())){
            System.out.format(">> ERROR [%d] %s is not class or array\n", line, typeName);
            printError();
        }

        if(!typeName.equals(t.name)){
            System.out.format(">> ERROR [%d] %s can't be variable\n", line, typeName);
            printError();
        }

        /*//array also include right?????????????????????????????????????????????????
        if(!t.isArray() && !t.isClass()){
            System.out.format(">> ERROR [%d] %s is not class or array type\n", line, typeName);
            printError(line);
            return null;
        }*/

        return t;
    }
    
}
