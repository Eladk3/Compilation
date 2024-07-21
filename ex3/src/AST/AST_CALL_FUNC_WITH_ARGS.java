package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_CALL_FUNC_WITH_ARGS extends AST_CALL_FUNC
{
    public String name;
    public AST_EXP_ARGS expArgs;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_CALL_FUNC_WITH_ARGS(String name, AST_EXP_ARGS expArgs, int lineNum)
    {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== callFunc -> ID LPAREN expArgs RPAREN\n");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.name = name;
        this.expArgs = expArgs;
    }

    /***************************************************/
    /* The printing message for a function call with arguments AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = FUNCTION CALL WITH ARGUMENTS */
        /*********************************/
        System.out.println("AST NODE FUNCTION CALL WITH ARGUMENTS");

        /******************************************/
        /* PRINT name */
        /******************************************/
        System.out.println("Function Name: " + name);

        /******************************************/
        /* RECURSIVELY PRINT expArgs */
        /******************************************/
        if (expArgs != null) expArgs.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "FUNCTION CALL WITH ARGUMENTS\nFunction Name: " + name);

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (expArgs != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, expArgs.SerialNumber);
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
        if(expArgs != null){
            args = expArgs.SemantMe();
        }

        if( !TYPE_LIST.sameListValuesAndOrder(args, functionType.params)){
            System.out.format(">> ERROR(%d): function call with wrong arguments type\n", line);
            printError();
        }
           
        return functionType.returnType;
        
        
    }
    
}
