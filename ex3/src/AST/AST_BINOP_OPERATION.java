package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_BINOP_OPERATION extends AST_BINOP {
    public int operation;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_BINOP_OPERATION(int operation, int lineNum) {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT operation in constructor */
        /***************************************/
        String binop = "====================== BINOP -> ";
        switch (operation) {
            case 0:
                System.out.println(binop + "PLUS\n");
                break;
            case 1:
                System.out.println(binop + "MINUS\n");
                break;
            case 2:
                System.out.println(binop + "TIMES\n");
                break;
            case 3:
                System.out.println(binop + "DIVIDE\n");
                break;
            case 4:
                System.out.println(binop + "LT\n");
                break;
            case 5:
                System.out.println(binop + "GT\n");
                break;
            case 6:
                System.out.println(binop + "EQ\n");
                break;
            default:
                System.out.println(binop + "UNKNOWN\n");
                break;
        }

        /***************************************/
        /* COPY INPUT DATA MEMBERS ... */
        /***************************************/
        this.operation = operation;

    }

    /***************************************************/
    /* The printing message for a binary operation AST node */
    /***************************************************/
    public void PrintMe() {
        /*********************************/
        /* AST NODE TYPE = BINARY OPERATION */
        /*********************************/
        System.out.print("AST NODE BINARY OPERATION\n");

        /******************************************/
        /* PRINT operation */
        /******************************************/
        System.out.print("Operation: ");
        switch (operation) {
            case 0:
                System.out.println("PLUS\n");
                break;
            case 1:
                System.out.println("MINUS\n");
                break;
            case 2:
                System.out.println("TIMES\n");
                break;
            case 3:
                System.out.println("DIVIDE\n");
                break;
            case 4:
                System.out.println("LT\n");
                break;
            case 5:
                System.out.println("GT\n");
                break;
            case 6:
                System.out.println("EQ\n");
                break;
            default:
                System.out.println("UNKNOWN\n");
                break;
        }

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "BINARY OPERATION\nOperation: " + operation);
    }

    public TYPE SemantMe() {
        return null;
    }


}
