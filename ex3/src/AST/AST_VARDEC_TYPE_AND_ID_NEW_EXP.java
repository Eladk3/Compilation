package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_VARDEC_TYPE_AND_ID_NEW_EXP extends AST_VARDEC
{
    public AST_TYPE type;
    public String name;
    public AST_NEW_EXP nex;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_VARDEC_TYPE_AND_ID_NEW_EXP(AST_TYPE type, String name, AST_NEW_EXP nex, int lineNum)
    {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("varDec -> type ID ASSIGN newExp SEMICOLON");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.type = type;
        this.name = name;
        this.nex = nex;
    }

    /***************************************************/
    /* The printing message for a variable declaration with type, ID, and new expression AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = VARDEC TYPE AND ID NEW EXP */
        /*********************************/
        System.out.println("AST NODE VARDEC TYPE AND ID NEW EXP");

        /******************************************/
        /* RECURSIVELY PRINT typAndId and nex ... */
        /******************************************/
        if (type != null) type.PrintMe();
        System.out.format("IDENTIFIER( %s )\n", name);
        if (nex != null) nex.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "VARDEC\nTYPE\nAND\nID\nNEW EXP");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (type != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, type.SerialNumber);
        if (nex != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, nex.SerialNumber);
    }

    public TYPE SemantMe() {
        TYPE t = type.SemantMe();
        TYPE t17 = null;
        boolean typeIsArray = false;
        if(t instanceof TYPE_VOID){
            System.out.format(">> ERROR [%d] variable %s can't be of type void\n",line ,name);
            printError();	
        }
        
        if (SYMBOL_TABLE.getInstance().findInCurrentScope(name) != null)
		{
			System.out.format(">> ERROR [%d] variable %s already exists in scope\n",line,name);
            printError();				
		}

        if(t instanceof TYPE_ARRAY){
            t17 = ((TYPE_ARRAY)t).typePointer;
            typeIsArray = true;
        }
        else{
            t17 = t;
        }

        TYPE t2 = nex.SemantMe();

        /*check types compatibility*/
        if ((!t17.equals(t2))) {
            if(!(t17 instanceof TYPE_CLASS)){
                System.out.format(">> ERROR [%d] incompatible types: %s cannot be converted to %s.\n", line, t2.name, t.name);
                printError();
            }
            else if (!((TYPE_CLASS)t17).isAncestorOf(t2)){
                System.out.format(">> ERROR [%d] Type mismatch in assignment: %s cannot be assigned to %s\n", line, t2.name, t17.name);
				printError();
            }
        }

        if (SYMBOL_TABLE.getInstance().getCurrentClass() != null && SYMBOL_TABLE.getInstance().getScope() == 1){
            System.out.format(">> ERROR [%d] initialized only with a constant value, new operation isn't allowed.\n", line);
            printError();
        }

        if(typeIsArray && !(nex instanceof AST_NEW_EXP_TYPE_EXP)){
            System.out.format(">> ERROR [%d] array should assign with array type.\n", line);
            printError();
        }

        if(!typeIsArray && !(nex instanceof AST_NEW_EXP_TYPE)){
            System.out.format(">> ERROR [%d] class should assign with class type, not array.\n", line);
            printError();
        }

        if(t2.isClass() && !SYMBOL_TABLE.getInstance().thisTypeIsClassDec(t2)){
            System.out.format(">> ERROR [%d] %s is variable\n", line, t2.name);
            printError();
        }

        SYMBOL_TABLE.getInstance().enter(name,t);

        return new TYPE_CLASS_VAR_DEC(t, name);
    }

}
