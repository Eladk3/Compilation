package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_ARRAY_TYPEDEF_SINGLE extends AST_ARRAY_TYPEDEF
{
    public String name;
    public AST_TYPE typ;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_ARRAY_TYPEDEF_SINGLE(String name, AST_TYPE typ, int lineNum)
    {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.format("====================== arrayTypedef -> ARRAY ID( %s ) EQ typei LBRACK RBRACK SEMICOLON\n", name);

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.name = name;
        this.typ = typ;
    }

    /***************************************************/
    /* The printing message for an array typedef AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = ARRAY TYPEDEF */
        /*********************************/
        System.out.println("AST NODE ARRAY TYPEDEF");

        /******************************************/
        /* PRINT name and type */
        /******************************************/
        System.out.format("ARRAY NAME( %s )\n", name);
        System.out.print("TYPE:\n");
        if (typ != null) typ.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            String.format("ARRAY\nTYPEDEF\n...->%s", name));

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (typ != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, typ.SerialNumber);
    }

    public TYPE SemantMe() {

        /*********************************************/
		/* [1] Semant the base type of the array ... */
		/*********************************************/
        TYPE t = typ.SemantMe();

        /***********************************************/
		/* [2] Make sure the base type is not void, null or nil... */
		/***********************************************/
        if (t == null || t instanceof TYPE_VOID || t instanceof TYPE_NIL) {
            System.out.format(">> ERROR [%d] non existing type\n", line);
            printError();
        }

        /*array type def must be global, 0 is global scope*/
        if (!(SYMBOL_TABLE.getInstance().getScope() == 0)) {
            System.out.format(">> ERROR [%d] array declared not in the global scope\n", line);
            printError();
        }

        

        /*check declaration*/
        if (SYMBOL_TABLE.getInstance().find(name) != null) {
            System.out.format(">> ERROR [%d] %s is already declared.\n", line, name);
            printError();
        }

        /**************************************************/
		/* [3] Enter the array type to the symbol table */
		/**************************************************/
        TYPE_ARRAY arr = new TYPE_ARRAY(t, name);
        SYMBOL_TABLE.getInstance().enter(name, arr);
        return arr;
    }

}
