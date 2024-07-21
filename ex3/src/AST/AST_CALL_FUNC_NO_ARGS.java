package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_CALL_FUNC_NO_ARGS extends AST_CALL_FUNC {
    public String name;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_CALL_FUNC_NO_ARGS(String name, int lineNum) {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== callFunc -> ID LPAREN RPAREN");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.name = name;
    }

    /***************************************************/
    /* The printing message for a function call without arguments AST node */
    /***************************************************/
    public void PrintMe() {
        /*********************************/
        /* AST NODE TYPE = FUNCTION CALL WITHOUT ARGUMENTS */
        /*********************************/
        System.out.println("AST NODE FUNCTION CALL WITHOUT ARGUMENTS");

        /******************************************/
        /* PRINT name */
        /******************************************/
        System.out.println("Function Name: " + name);

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "FUNCTION CALL WITHOUT ARGUMENTS\nName: " + name);
    }

    public TYPE SemantMe() {
        
        TYPE founded = SYMBOL_TABLE.getInstance().find(name);
        
        if(founded == null || !(founded instanceof TYPE_FUNCTION)){
            System.out.format(">> ERROR(%d): function %s was not declared\n", this.line, name);
            printError();
        }

        TYPE_FUNCTION functionType = (TYPE_FUNCTION)founded;
    
        /***********************************/
        /* [2] Semant the Function Call */
        /***********************************/
        TYPE_LIST args = null;

        if( !TYPE_LIST.sameListValuesAndOrder(args, functionType.params)){
            System.out.format(">> ERROR(%d): function call with wring arguments type\n", line);
            printError();
        }
           
        return functionType.returnType;
        
        
    }
}
