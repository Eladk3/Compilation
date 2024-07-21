package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_VAR_FIELD extends AST_VAR
{
	public AST_VAR_AND_DOT var;
	public String fieldName;
	private TYPE field = null;
	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_VAR_FIELD(AST_VAR_AND_DOT var,String fieldName, int lineNum)
	{
		super(lineNum);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.format("====================== var -> var DOT ID( %s )\n",fieldName);

		/*******************************/
		/* COPY INPUT DATA NENBERS ... */
		/*******************************/
		this.var = var;
		this.fieldName = fieldName;
	}

	/*************************************************/
	/* The printing message for a field var AST node */
	/*************************************************/
	public void PrintMe()
	{
		/*********************************/
		/* AST NODE TYPE = AST FIELD VAR */
		/*********************************/
		System.out.print("AST NODE FIELD VAR\n");

		/**********************************************/
		/* RECURSIVELY PRINT VAR, then FIELD NAME ... */
		/**********************************************/
		if (var != null) var.PrintMe();
		System.out.format("FIELD NAME( %s )\n",fieldName);

		/***************************************/
		/* PRINT Node to AST GRAPHVIZ DOT file */
		/***************************************/
		AST_GRAPHVIZ.getInstance().logNode(
			SerialNumber,
			String.format("FIELD\nVAR\n...->%s",fieldName));
		
		/****************************************/
		/* PRINT Edges to AST GRAPHVIZ DOT file */
		/****************************************/
		if (var != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber,var.SerialNumber);
	}

	public TYPE SemantMe() {
		/******************************/
		/* [1] Recursively semant var */
		/******************************/
        TYPE t = var.SemantMe();

        /*********************************/
		/* [2] Make sure type is a class */
		/*********************************/
        if (!(t.isClass())) {
			System.out.println("BLAH\n");
            System.out.format(">> ERROR [%d] %s is not a class type\n", line, t.name);
            printError();
            return null;
        }

		

        /************************************/
		/* [3] Look for fiedlName inside tc */
		/************************************/
        TYPE_CLASS tc = (TYPE_CLASS) t;
		field = tc.getDataMember(fieldName);
		
		

        /*********************************************/
		/* [4] fieldName does not exist in class var */
		/*********************************************/
		if (field == null) {
			System.out.println("ewijofiururuirhugil");
			if (SYMBOL_TABLE.getInstance().getCurrentClass() != null && SYMBOL_TABLE.getInstance().getCurrentClass().equals(t)){
				System.out.println("im in");
				TYPE t2 = SYMBOL_TABLE.getInstance().findInScopeDownTo(fieldName, 1);
				if(t2 == null){
					System.out.format(">> ERROR [%d] %s is not a member of class yes %s\n", line, fieldName, t.name);
					printError();
				}
				return t2;
			}
			else{
				System.out.format(">> ERROR [%d] %s is not a member of class %s\n", line, fieldName, t.name);
				printError();
				return null;
			}
		}

		return field;
    }
}
