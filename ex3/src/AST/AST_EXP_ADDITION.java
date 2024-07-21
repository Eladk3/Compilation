package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;


public class AST_EXP_ADDITION extends AST_EXP {
    public AST_EXP e1;
    public AST_EXP e2;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_EXP_ADDITION(AST_EXP e1, AST_EXP e2, int lineNum) {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== exp -> exp PLUS exp\n");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.e1 = e1;
        this.e2 = e2;
    }

    /***************************************************/
    /* The printing message for an addition expression AST node */
    /***************************************************/
    public void PrintMe() {
        /*********************************/
        /* AST NODE TYPE = ADDITION */
        /*********************************/
        System.out.println("AST NODE ADDITION");

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
                "ADDITION");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (e1 != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, e1.SerialNumber);
        if (e2 != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, e2.SerialNumber);
    }

    public TYPE SemantMe() {
        TYPE t1 = e1.SemantMe();
        TYPE t2 = e2.SemantMe();
    
        if (t1 instanceof TYPE_INT && t2 instanceof TYPE_INT) {
            return TYPE_INT.getInstance();
        }

        if (t1 instanceof TYPE_STRING && t2 instanceof TYPE_STRING) {
            return TYPE_STRING.getInstance();
        }
    
        System.out.format(">> ERROR(%d): plus only between integers or strings\n", line);
        printError();
        return null;
    }

    @Override
	public boolean isConst() {
		return false;
	}	
    
}

