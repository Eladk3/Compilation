package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_STMT_ASSIGN_NEW_EXP extends AST_STMT
{
    public AST_VAR var;
    public AST_NEW_EXP newExp;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_STMT_ASSIGN_NEW_EXP(AST_VAR var, AST_NEW_EXP newExp, int lineNum)
    {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== stmt -> var ASSIGN newExp SEMICOLON");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.var = var;
        this.newExp = newExp;
    }

    /***************************************************/
    /* The printing message for an assignment statement with new expression AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = STATEMENT ASSIGNMENT NEW EXPRESSION */
        /*********************************/
        System.out.println("AST NODE STATEMENT ASSIGNMENT NEW EXPRESSION");

        /******************************************/
        /* RECURSIVELY PRINT var and newExp ... */
        /******************************************/
        if (var != null) var.PrintMe();
        if (newExp != null) newExp.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "STATEMENT\nASSIGNMENT NEW EXPRESSION");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (var != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, var.SerialNumber);
        if (newExp != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, newExp.SerialNumber);
    }

    public TYPE SemantMe() {
        /***********************************************/
        /* [1] Semantically analyze the left side (var) of the assignment */
        /***********************************************/
        TYPE varType = var.SemantMe();
        boolean typeIsArray = false;
    
        /***********************************************/
        /* [2] Semantically analyze the new expression */
        /***********************************************/
        TYPE newExpType = newExp.SemantMe();
        

        TYPE t17 = null;

        if(varType instanceof TYPE_ARRAY){
            t17 = ((TYPE_ARRAY)varType).typePointer;
            typeIsArray = true;
        }
        else{
            t17 = varType;
        }

        System.out.println("wowwowowww");

        /*******************************************************/
        /* [3] Make sure the new expression type matches the variable type */
        /*******************************************************/
        if (!t17.equals(newExpType)) {
            System.out.println("wow3");

            if (!(t17 instanceof TYPE_CLASS)){
                System.out.format(">> ERROR: [%d] Type mismatch in assignment: Cannot assign %s to %s\n",line, newExpType.name, varType.name);
                printError();
            }
            else if ( !((TYPE_CLASS)t17).isAncestorOf(newExpType)){
                System.out.format(">> ERROR [%d] Type mismatch in assignment: %s cannot be assigned to %s\n", line, newExpType.name, t17.name);
				printError();
            }

        }

        if(typeIsArray && !(newExp instanceof AST_NEW_EXP_TYPE_EXP)){
            System.out.format(">> ERROR [%d] array should assign with array type.\n", line);
            printError();
        }

        if(!typeIsArray && !(newExp instanceof AST_NEW_EXP_TYPE)){
            System.out.format(">> ERROR [%d] class should assign with class type, not array.\n", line);
            printError();
        }
    
        /**************************************************/
        /* [4] Return value is irrelevant for assignments */
        /**************************************************/
        return null;
    }
    
}
