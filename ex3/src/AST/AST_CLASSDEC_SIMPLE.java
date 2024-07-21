package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_CLASSDEC_SIMPLE extends AST_CLASSDEC
{
    public String name;
    public AST_CFIELD_LIST body;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_CLASSDEC_SIMPLE(String name, AST_CFIELD_LIST body, int lineNum)
    {
        super(lineNum);
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
    public TYPE SemantMe() {

        /*class declaration must be global, 0 is global scope*/
        if (!(SYMBOL_TABLE.getInstance().getScope() == 0)) {
            System.out.format(">> ERROR [%d] class declared not in the global scope\n", line);
            printError();
        }
        SYMBOL_TABLE.getInstance().enter(name, new TYPE_CLASS(null, name, new TYPE_LIST(null, null)), true);//for field use this class

        /*************************/
        /* [1] Begin Class Scope */
        /*************************/
        SYMBOL_TABLE.getInstance().beginScope();
        SYMBOL_TABLE.getInstance().setCurrentClass(new TYPE_CLASS(null, name, new TYPE_LIST(null, null)));

        /***************************/
        /* [2] Semant Data Members */
        /***************************/

        TYPE_CLASS t = new TYPE_CLASS(null, name, body.SemantMe());
    
        /*****************/
        /* [3] End Scope */
        /*****************/
        SYMBOL_TABLE.getInstance().setCurrentClass(null);
        SYMBOL_TABLE.getInstance().endScope();
    
        /************************************************/
        /* [4] Enter the Class Type to the Symbol Table */
        /************************************************/
        SYMBOL_TABLE.getInstance().enter(name, t, true);
    
        /*********************************************************/
        /* [5] Return value is irrelevant for class declarations */
        /*********************************************************/
        return null;
    }
    
}
