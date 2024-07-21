package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_EXP_BINOP extends AST_EXP
{
    public AST_EXP e1;
    public AST_BINOP op;
    public AST_EXP e2;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_EXP_BINOP(AST_EXP e1, AST_BINOP op, AST_EXP e2, int lineNum)
    {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== exp -> exp BINOP exp");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.e1 = e1;
        this.op = op;
        this.e2 = e2;
    }

    /***************************************************/
    /* The printing message for a binary operation expression AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = EXPRESSION BINOP */
        /*********************************/
        System.out.println("AST NODE EXPRESSION BINOP");

        /******************************************/
        /* RECURSIVELY PRINT e1 and e2 ... */
        /******************************************/
        if (e1 != null) e1.PrintMe();
        if (op != null) op.PrintMe();
        if (e2 != null) e2.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "EXPRESSION\nBINOP");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (e1 != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, e1.SerialNumber);
        if (op != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, op.SerialNumber);
        if (e2 != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, e2.SerialNumber);
    }

    public TYPE SemantMe() {
        TYPE t1 = e1.SemantMe();
        TYPE t2 = e2.SemantMe();

        //operation == 3 is divide
        if (op instanceof AST_BINOP_OPERATION && ((AST_BINOP_OPERATION)op).operation == 3){
            if(e2 instanceof AST_EXP_INT && ((AST_EXP_INT)e2).value == 0){
                System.out.format(">> ERROR(%d): divide in zero illegal\n", line);
                printError();
            }
        }


        TYPE tmp1 =null, tmp2= null;
        System.out.println("t1 = " + t1.name + " t2 = " + t2.name + " LINE NUMBER: " + line);
        if (t1 instanceof TYPE_INT && t2 instanceof TYPE_INT) {
            return TYPE_INT.getInstance();
        }

        

        System.out.format(">> ERROR(%d): operation (except plus) only between integers\n", line);
        printError();
        return null;
    }

    @Override
	public boolean isConst() {
		return false;
	}	
}
