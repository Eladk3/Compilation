package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_CLASSDEC_INHERITANCE extends AST_CLASSDEC
{
    public String name;
    public String superClass;
    public AST_CFIELD_LIST body;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_CLASSDEC_INHERITANCE(String name, String superClass, AST_CFIELD_LIST body, int lineNum)
    {
        super(lineNum);
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
    public TYPE SemantMe() {

        /*class declaration must be global, 0 is global scope*/
        if (!(SYMBOL_TABLE.getInstance().getScope() == 0)) {
            System.out.format(">> ERROR [%d] class declared not in the global scope\n", line);
            printError();
        }
        
        /*************************/
        /* [1] Begin Class Scope */
        /*************************/
        

    
        /***************************/
        /* [2] Semant Data Members */
        /***************************/
        TYPE fatherT = SYMBOL_TABLE.getInstance().find(superClass);
        System.out.println("father: " + fatherT.name);
        System.out.println("superClass: " + superClass);

        if(fatherT == null || !fatherT.isClass() || !superClass.equals(fatherT.name)){
            System.out.format(">> ERROR [%d] inhertince incorect.\n", line);
            printError();
        }
        
        
        TYPE_CLASS father = (TYPE_CLASS) fatherT;
        SYMBOL_TABLE.getInstance().setCurrentClass(new TYPE_CLASS(father, name, new TYPE_LIST(null, null)));
        SYMBOL_TABLE.getInstance().enter(name, new TYPE_CLASS(father, name, new TYPE_LIST(null, null)), true);//for field use this class
        SYMBOL_TABLE.getInstance().beginScope();
        TYPE_CLASS t = new TYPE_CLASS(father, name, body.SemantMe());
        
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
