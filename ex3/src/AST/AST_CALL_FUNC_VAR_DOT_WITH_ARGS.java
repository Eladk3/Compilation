package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_CALL_FUNC_VAR_DOT_WITH_ARGS extends AST_CALL_FUNC
{
    public AST_VAR_AND_DOT varDot;
    public String name;
    public AST_EXP_ARGS expArgs;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_CALL_FUNC_VAR_DOT_WITH_ARGS(AST_VAR_AND_DOT varDot, String name, AST_EXP_ARGS expArgs, int lineNum)
    {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== callFunc -> varDot ID LPAREN expArgs RPAREN\n");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.varDot = varDot;
        this.name = name;
        this.expArgs = expArgs;
    }

    /***************************************************/
    /* The printing message for a function call with variable and dot and arguments AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = FUNCTION CALL WITH VAR AND DOT AND ARGUMENTS */
        /*********************************/
        System.out.println("AST NODE FUNCTION CALL WITH VAR AND DOT AND ARGUMENTS");

        /******************************************/
        /* RECURSIVELY PRINT varDot and name */
        /******************************************/
        if (varDot != null) varDot.PrintMe();
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
            "FUNCTION CALL WITH VAR AND DOT AND ARGUMENTS\nFunction Name: " + name);

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (varDot != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, varDot.SerialNumber);
        if (expArgs != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, expArgs.SerialNumber);
    }

    public TYPE SemantMe() {

        TYPE_FUNCTION functionType = null;

        TYPE varT = varDot.SemantMe();

        if(!(varT instanceof TYPE_CLASS)){
            System.out.format(">> ERROR(%d): var isn't class type\n", line);
            printError();
        }

        TYPE_CLASS objectType = (TYPE_CLASS)varT;
        TYPE tmp = SYMBOL_TABLE.getInstance().findInCurrentScope(name);
        TYPE tmp1 = objectType.data_members.search(name);

        if(tmp != null && tmp instanceof TYPE_FUNCTION){
            functionType = (TYPE_FUNCTION)tmp;
        }
        else if (tmp1 != null && tmp1 instanceof TYPE_FUNCTION){
            functionType = (TYPE_FUNCTION)tmp1;
        }
        else{
            System.out.format(">> ERROR(%d): method not exist\n", line);
            printError();
        }
                
        /***********************************/
        /* [2] Semant the Function Call */
        /***********************************/
        TYPE_LIST args = null;
        if(expArgs != null){
            args = expArgs.SemantMe();
        }

        if( !TYPE_LIST.sameListValuesAndOrder(args, functionType.params)){
            System.out.format(">> ERROR(%d): function call with wring arguments type\n", line);
            printError();
        }
           
        return functionType.returnType;
        
    }
    
}
