package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_FUNC_ARGS extends AST_Node {

    public AST_TYPE type;
    public String name;
    public AST_FUNC_ARGS funcArgs;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_FUNC_ARGS(AST_TYPE type, String name, AST_FUNC_ARGS funcArgs, int lineNum) {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        if(funcArgs != null) System.out.println("funcArgs -> type ID COMMA funcArgs");
        else System.out.println("funcArgs -> type ID");
        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.type = type;
        this.name = name;
        this.funcArgs = funcArgs;
    }

    /***************************************************/
    /* The printing message for function arguments AST node */
    /***************************************************/
    public void PrintMe() {
        /*********************************/
        /* AST NODE TYPE = FUNCTION ARGUMENTS */
        /*********************************/
        System.out.println("AST NODE FUNCTION ARGUMENTS");

        /******************************************/
        /* RECURSIVELY PRINT typeID and funcArgs ... */
        /******************************************/
        if (type != null) type.PrintMe();
        System.out.format("IDENTIFIER( %s )\n", name);
        if (funcArgs != null) funcArgs.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "FUNCTION ARGUMENTS");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (type != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, type.SerialNumber);
        if (funcArgs != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, funcArgs.SerialNumber);
    }

    public TYPE_LIST SemantMe() {
        /***************************************/
        /* [1] Semantically analyze the type of the argument */
        /***************************************/
        TYPE argumentType = type.SemantMe();
        
        if (argumentType instanceof TYPE_VOID){
            System.out.format(">> ERROR [%d] param can't be null.\n", line);
            printError();
        }

        if (SYMBOL_TABLE.getInstance().findInCurrentScope(name) != null)
		{
			System.out.format(">> ERROR [%d] variable %s already exists in scope\n",line,name);
            printError();				
		}
        /***************************************/
        /* [2] Add the argument to the symbol table */
        /***************************************/
        SYMBOL_TABLE.getInstance().enter(name, argumentType);
    
        /***************************************/
        /* [3] Semantically analyze the rest of the arguments */
        /***************************************/
        if (funcArgs == null)
		{
			return new TYPE_LIST(
				argumentType,
				null);
		}
		else
		{
			return new TYPE_LIST(
				argumentType,
				funcArgs.SemantMe());
		}
    }
    
}
