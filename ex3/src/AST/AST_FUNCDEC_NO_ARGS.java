package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_FUNCDEC_NO_ARGS extends AST_FUNCDEC
{
    public AST_TYPE type;
    public String name;
    public AST_STMT_LIST body;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_FUNCDEC_NO_ARGS(AST_TYPE type, String name, AST_STMT_LIST body, int lineNum)
    {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== funcDec -> type ID LPAREN RPAREN LBRACE stmtList RBRACE");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.type = type;
        this.name = name;
        this.body = body;
    }

    /***************************************************/
    /* The printing message for a function declaration with no arguments AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = FUNCDEC NO ARGS */
        /*********************************/
        System.out.println("AST NODE FUNCDEC NO ARGS");

        /******************************************/
        /* RECURSIVELY PRINT typAndId and body ... */
        /******************************************/
        if (type != null) type.PrintMe();
        System.out.format("IDENTIFIER( %s )\n", name);
        if (body != null) body.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "FUNCDEC\nNO ARGS");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (type != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, type.SerialNumber);
        if (body != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, body.SerialNumber);
    }

    public TYPE SemantMe() {
        /***************************************/
        /* [1] Semantically analyze the return type and function identifier */
        /***************************************/
        TYPE returnType = type.SemantMe();
        
        if (SYMBOL_TABLE.getInstance().findInCurrentScope(name) != null)
		{
			System.out.format(">> ERROR [%d] function %s already exists in scope\n",line,name);
            printError();				
		}

        TYPE_FUNCTION currFunc;
        currFunc = new TYPE_FUNCTION(returnType,name,null);

        if(SYMBOL_TABLE.getInstance().getCurrentClass() != null){
            if(!isCurrentFunctionOverloadingCorrect(SYMBOL_TABLE.getInstance().getCurrentClass(), currFunc)){
                System.out.format(">> ERROR [%d] function %s overloading isn't correct\n",line,name);
                printError();	
            }
            System.out.println("in funcdecwithnoargs");
            TYPE_CLASS tyClass = SYMBOL_TABLE.getInstance().getCurrentClass();
            if(tyClass.getDataMember(name) != null && !(tyClass.getDataMember(name) instanceof TYPE_FUNCTION)){
                System.out.format(">> ERROR [%d] shadowing %s is illegal.\n", line, name);
                printError();
            }
        }
        /***************************************/
        /* [2] Begin a new function scope */
        /***************************************/
        SYMBOL_TABLE.getInstance().beginScope();
        SYMBOL_TABLE.getInstance().setIsInFunction(true);
        
        

        SYMBOL_TABLE.getInstance().setCurrentFunction(currFunc);

        /***************************************/
        /* [3] Semantically analyze the function body */
        /***************************************/
        SYMBOL_TABLE.getInstance().enter(name,currFunc);//for recursion
        body.SemantMe();
    
        /***************************************/
        /* [4] End the function scope */
        /***************************************/
        SYMBOL_TABLE.getInstance().endScope();
        SYMBOL_TABLE.getInstance().setIsInFunction(false);
        
        /***************************************************/
		/* [5] Enter the Function Type to the Symbol Table */
		/***************************************************/
		SYMBOL_TABLE.getInstance().enter(name,currFunc);

        /***************************************/
        /* [5] Return type of the function */
        /***************************************/
        return new TYPE_CLASS_VAR_DEC(new TYPE_FUNCTION(returnType, name, null), name);

        
    }

    public static boolean isCurrentFunctionOverloadingCorrect(TYPE_CLASS currentClass, TYPE_FUNCTION currFunc) {
        // Traverse the inheritance hierarchy
        TYPE_CLASS ancestorClass = currentClass.father;
        while (ancestorClass != null) {
            // Check if the ancestor class has a function with the same name
            System.out.println("func name is: "+ currFunc.name);
            TYPE_FUNCTION ancestorFunction = ancestorClass.getMethod(currFunc.name);
            if (ancestorFunction != null) {
                // Check if the function signatures match
                if (!ancestorFunction.equalsSignature(currFunc)) {
                    return false; // Overloading not correct
                }
            }
            ancestorClass = ancestorClass.father; // Move to the next ancestor
        }
        return true; // Overloading is correct
    }
    
    
}
