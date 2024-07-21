package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_FUNCDEC_WITH_ARGS extends AST_FUNCDEC
{
    public AST_TYPE type;
    public String name;
    public AST_FUNC_ARGS args;
    public AST_STMT_LIST body;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_FUNCDEC_WITH_ARGS(AST_TYPE type, String name, AST_FUNC_ARGS args, AST_STMT_LIST body, int lineNum)
    {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== funcDec -> type ID LPAREN funcArgs RPAREN LBRACE stmtList RBRACE");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.type = type;
        this.name = name;
        this.args = args;
        this.body = body;
    }

    /***************************************************/
    /* The printing message for a function declaration with arguments AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = FUNCDEC WITH ARGS */
        /*********************************/
        System.out.println("AST NODE FUNCDEC WITH ARGS");

        /******************************************/
        /* RECURSIVELY PRINT typAndId, args, and body ... */
        /******************************************/
        if (type != null) type.PrintMe();
        System.out.format("IDENTIFIER( %s )\n", name);
        if (args != null) args.PrintMe();
        if (body != null) body.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "FUNCDEC\nWITH ARGS");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (type != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, type.SerialNumber);
        if (args != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, args.SerialNumber);
        if (body != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, body.SerialNumber);
    }

    public TYPE SemantMe() {
        /***************************************/
        /* [1] Semantically analyze the return type and function identifier */
        /***************************************/
        TYPE returnType = type.SemantMe();
        System.out.println("in funcdecwithargs");
        if (SYMBOL_TABLE.getInstance().findInCurrentScope(name) != null)
		{
			System.out.format(">> ERROR [%d] function with args %s already exists in scope\n",line,name);
            printError();				
		}
        TYPE_CLASS tyClass = SYMBOL_TABLE.getInstance().getCurrentClass();
        if(tyClass != null && tyClass.getDataMember(name) != null && !(tyClass.getDataMember(name) instanceof TYPE_FUNCTION)){
            System.out.format(">> ERROR [%d] shadowing %s is illegal.\n", line, name);
            printError();
        }


        SYMBOL_TABLE.getInstance().beginScope();
        TYPE_LIST funcParams;
        TYPE_FUNCTION currFunc;
        if (args != null) {
            funcParams = (TYPE_LIST)args.SemantMe();
        }
        else{
            funcParams = null;
        }
        currFunc = new TYPE_FUNCTION(returnType,name,funcParams);

        if(SYMBOL_TABLE.getInstance().getCurrentClass() != null){
            if(!AST_FUNCDEC_NO_ARGS.isCurrentFunctionOverloadingCorrect(SYMBOL_TABLE.getInstance().getCurrentClass(), currFunc)){
                System.out.format(">> ERROR [%d] function with args %s overloading isn't correct\n",line,name);
                printError();	
            }
        }
        SYMBOL_TABLE.getInstance().endScope();
        /***************************************/
        /* [2] Begin a new function scope */
        /***************************************/
        SYMBOL_TABLE.getInstance().beginScope();
        SYMBOL_TABLE.getInstance().setIsInFunction(true);
        /***************************************/
        /* [3] Semantically analyze the function arguments */
        /***************************************/
        if (args != null) {
            funcParams = (TYPE_LIST)args.SemantMe();
        }
        else{
            funcParams = null;
        }
        currFunc = new TYPE_FUNCTION(returnType,name,funcParams);
        SYMBOL_TABLE.getInstance().setCurrentFunction(currFunc);
        /***************************************/
        /* [4] Semantically analyze the function body */
        /***************************************/
        SYMBOL_TABLE.getInstance().enter(name, currFunc);//for recursion
        body.SemantMe();
    
        /***************************************/
        /* [5] End the function scope */
        /***************************************/
        SYMBOL_TABLE.getInstance().endScope();
        SYMBOL_TABLE.getInstance().setIsInFunction(false);
        

        /***************************************************/
		/* [5] Enter the Function Type to the Symbol Table */
		/***************************************************/
		SYMBOL_TABLE.getInstance().enter(name, currFunc);
        /***************************************/
        /* [6] Return type of the function */
        /***************************************/
        return new TYPE_CLASS_VAR_DEC(new TYPE_FUNCTION(returnType, name, funcParams), name);
        
    }
    
}

