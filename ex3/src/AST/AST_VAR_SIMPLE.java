package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_VAR_SIMPLE extends AST_VAR
{
	/************************/
	/* simple variable name */
	/************************/
	public String name;
	
	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AST_VAR_SIMPLE(String name, int lineNum)
	{
		super(lineNum);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
	
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.format("====================== var -> ID( %s )\n",name);

		/*******************************/
		/* COPY INPUT DATA NENBERS ... */
		/*******************************/
		this.name = name;
	}

	/**************************************************/
	/* The printing message for a simple var AST node */
	/**************************************************/
	public void PrintMe()
	{
		/**********************************/
		/* AST NODE TYPE = AST SIMPLE VAR */
		/**********************************/
		System.out.format("AST NODE SIMPLE VAR( %s )\n",name);

		/*********************************/
		/* Print to AST GRAPHIZ DOT file */
		/*********************************/
		AST_GRAPHVIZ.getInstance().logNode(
			SerialNumber,
			String.format("SIMPLE\nVAR\n(%s)",name));
	}

	public TYPE SemantMe() {
		TYPE_CLASS classType = SYMBOL_TABLE.getInstance().getCurrentClass();
		TYPE t;
		if (classType != null) {
			t = SYMBOL_TABLE.getInstance().findInScopeDownTo(name, 1);
			if (t == null) {
				t = classType.getDataMember(name);
			}
			if (t == null) {
				t = SYMBOL_TABLE.getInstance().find(name);
			}
			
		} else {
			t = SYMBOL_TABLE.getInstance().find(name);

		}

        if (t == null) {
            System.out.format(">> ERROR [%d] variable %s is not declared\n", line, name);
            printError();
            return null;
        }

		System.out.println("in var simple "+line + " value is: " + SYMBOL_TABLE.getInstance().findClassDec(name));

		if (SYMBOL_TABLE.getInstance().findClassDec(name) != null && SYMBOL_TABLE.getInstance().thisTypeIsClassDec(t)) {
            System.out.format(">> ERROR [%d] class %s can't be a variable\n", line, name);
            printError();
            return null;
        }

		System.out.format("Inside AST_VAR_SIMPLE the return type name is %s\n", t.name);
        return t;
    }

}
