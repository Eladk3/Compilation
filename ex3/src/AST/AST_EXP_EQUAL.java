package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;


public class AST_EXP_EQUAL extends AST_EXP {
    public AST_EXP e1;
    public AST_EXP e2;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_EXP_EQUAL(AST_EXP e1, AST_EXP e2, int lineNum) {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== exp -> exp EQ exp\n");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.e1 = e1;
        this.e2 = e2;
    }

    /***************************************************/
    /* The printing message for an equality expression AST node */
    /***************************************************/
    public void PrintMe() {
        /*********************************/
        /* AST NODE TYPE = EQUALITY */
        /*********************************/
        System.out.println("AST NODE EQUALITY");

        /******************************************/
        /* RECURSIVELY PRINT e1 and e2 */
        /******************************************/
        if (e1 != null) e1.PrintMe();
        if (e2 != null) e2.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "EQUALITY");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (e1 != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, e1.SerialNumber);
        if (e2 != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, e2.SerialNumber);
    }

    public TYPE SemantMe() {
        TYPE t1 = e1.SemantMe();
        TYPE t2 = e2.SemantMe();
        if(t1 == null || t2 == null){
            System.out.format(">> ERROR(%d): cannot compare null types\n", this.line);
            printError();
        }

        if (!t1.isEqualsOrSubTypeOf(t2) && !t2.isEqualsOrSubTypeOf(t1)) {
            System.out.format(">> ERROR(%d): cannot compare different types\n", this.line);
            printError();
        }
    
        return TYPE_INT.getInstance();
    }    

    @Override
	public boolean isConst() {
		return false;
	}	
}
