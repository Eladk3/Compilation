package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_VARDEC_TYPE_AND_ID_EXP extends AST_VARDEC
{
    public AST_TYPE type;
    public String name;
    public AST_EXP ex;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_VARDEC_TYPE_AND_ID_EXP(AST_TYPE type, String name, AST_EXP ex, int lineNum)
    {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== varDec -> type ID ASSIGN exp SEMICOLON");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.type = type;
        this.name = name;
        this.ex = ex;
    }

    /***************************************************/
    /* The printing message for a variable declaration with type, ID, and expression AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = VARDEC TYPE AND ID EXP */
        /*********************************/
        System.out.println("AST NODE VARDEC TYPE AND ID EXP");

        /******************************************/
        /* RECURSIVELY PRINT typAndId and ex ... */
        /******************************************/
        if (type != null) type.PrintMe();
        System.out.format("IDENTIFIER( %s )\n", name);
        if (ex != null) ex.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "VARDEC\nTYPE\nAND\nID\nEXP");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (type != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, type.SerialNumber);
        if (ex != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, ex.SerialNumber);
    }
    
    public TYPE SemantMe() {
        TYPE t = type.SemantMe();

        if(t instanceof TYPE_VOID){
            System.out.format(">> ERROR [%d] variable %s can't be of type void\n",line ,name);
            printError();	
        }

        if (SYMBOL_TABLE.getInstance().findInCurrentScope(name) != null)
		{
			System.out.format(">> ERROR [%d:%d] variable %s already exists in scope\n",2,2,name);
            printError();				
		}
        TYPE t2 = ex.SemantMe();

        /*check types compatibility*/
        System.out.println((!t.equals(t2)));
        System.out.println(!(t2 instanceof TYPE_NIL && TYPE_NIL.canAssignNil(t)));
        System.out.println( ((t instanceof TYPE_CLASS) && !((TYPE_CLASS)t).isAncestorOf(t2)));

        if ((!t.equals(t2)) && !(t2 instanceof TYPE_NIL && TYPE_NIL.canAssignNil(t))) {

            if (!(t instanceof TYPE_CLASS)){
                System.out.format(">> ERROR [%d] incompatible types: %s cannot be converted to %s.\n", line, t2.name, t.name);
                printError();
            }
            else if(!((TYPE_CLASS)t).isAncestorOf(t2)){
                System.out.format(">> ERROR [%d] incompatible types: %s cannot be converted to %s.\n", line, t2.name, t.name);
                printError();
            }
        }

        if (SYMBOL_TABLE.getInstance().getCurrentClass() != null && SYMBOL_TABLE.getInstance().getScope() == 1){
            //in class scope
            if(!ex.isConst()){
                System.out.format(">> ERROR [%d] initialized only with a constant value, %s isn't constant.\n", line, t2.name);
                printError();
            }

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
