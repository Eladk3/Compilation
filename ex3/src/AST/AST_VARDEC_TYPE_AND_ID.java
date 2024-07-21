package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_VARDEC_TYPE_AND_ID extends AST_VARDEC
{
    public AST_TYPE type;
    public String name;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_VARDEC_TYPE_AND_ID(AST_TYPE type, String name, int lineNum)
    {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("varDec -> type ID SEMICOLON");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.type = type;
        this.name = name;
    }

    /***************************************************/
    /* The printing message for a variable declaration with type and ID AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = VARDEC TYPE AND ID */
        /*********************************/
        System.out.println("AST NODE VARDEC TYPE AND ID");

        /******************************************/
        /* RECURSIVELY PRINT type And Id ... */
        /******************************************/
        if (type != null) type.PrintMe();
        System.out.format("IDENTIFIER( %s )\n", name);

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "VARDEC\nTYPE\nAND\nID");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (type != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, type.SerialNumber);
    }

    public TYPE SemantMe() {
        TYPE t = type.SemantMe();

        if(t instanceof TYPE_VOID){
            System.out.format(">> ERROR [%d] variable %s can't be of type void\n",line ,name);
            printError();	
        }

        if (SYMBOL_TABLE.getInstance().findInCurrentScope(name) != null)
		{
			System.out.format(">> ERROR [%d] variable %s already exists in scope\n",line,name);
            printError();				
		}

        if (SYMBOL_TABLE.getInstance().getCurrentClass() != null && SYMBOL_TABLE.getInstance().getScope() == 1){
            TYPE_CLASS tyClass = SYMBOL_TABLE.getInstance().getCurrentClass();
            if(tyClass.getDataMember(name) != null){
                System.out.format(">> ERROR [%d] shadowing %s is illegal.\n", line, name);
                printError();
            }
        }

        SYMBOL_TABLE.getInstance().enter(name,t);

        return new TYPE_CLASS_VAR_DEC(t, name);
    }

}
