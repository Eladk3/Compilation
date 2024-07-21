package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_STMT_ASSIGN extends AST_STMT
{
	/***************/
	/*  var := exp */
	/***************/
	public AST_VAR var;
	public AST_EXP exp;

	/*******************/
	/*  CONSTRUCTOR(S) */
	/*******************/
	public AST_STMT_ASSIGN(AST_VAR var,AST_EXP exp, int lineNum)
	{
		super(lineNum);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== stmt -> var ASSIGN exp SEMICOLON\n");

		/*******************************/
		/* COPY INPUT DATA NENBERS ... */
		/*******************************/
		this.var = var;
		this.exp = exp;
	}

	/*********************************************************/
	/* The printing message for an assign statement AST node */
	/*********************************************************/
	public void PrintMe()
	{
		/********************************************/
		/* AST NODE TYPE = AST ASSIGNMENT STATEMENT */
		/********************************************/
		System.out.print("AST NODE ASSIGN STMT\n");

		/***********************************/
		/* RECURSIVELY PRINT VAR + EXP ... */
		/***********************************/
		if (var != null) var.PrintMe();
		if (exp != null) exp.PrintMe();

		/***************************************/
		/* PRINT Node to AST GRAPHVIZ DOT file */
		/***************************************/
		AST_GRAPHVIZ.getInstance().logNode(
			SerialNumber,
			"ASSIGN\nleft := right\n");
		
		/****************************************/
		/* PRINT Edges to AST GRAPHVIZ DOT file */
		/****************************************/
		AST_GRAPHVIZ.getInstance().logEdge(SerialNumber,var.SerialNumber);
		AST_GRAPHVIZ.getInstance().logEdge(SerialNumber,exp.SerialNumber);
	}

	public TYPE SemantMe() {
		/***************************************/
		/* [1] Semantically analyze the left side (var) of the assignment */
		/***************************************/
		TYPE varType = var.SemantMe();
	
		/***************************************/
		/* [2] Semantically analyze the right side (exp) of the assignment */
		/***************************************/
		TYPE expType = exp.SemantMe();
	
		/*******************************************************/
		/* [3] Make sure types on both sides are compatible    */
		/*******************************************************/
		/*System.out.println("line is: " + line);
		System.out.println("varType is: " + varType.name);
		System.out.println("expType is: " + expType.name);
		
		
		System.out.println("1: " + (!expType.equals(varType)));
		System.out.println("2: " + !(expType instanceof TYPE_NIL && TYPE_NIL.canAssignNil(varType)));
		System.out.println("3: " + ((varType instanceof TYPE_CLASS) && !((TYPE_CLASS)varType).isAncestorOf(expType)));*/
		if ((!expType.equals(varType)) && !(expType instanceof TYPE_NIL && TYPE_NIL.canAssignNil(varType)) ) {
			System.out.println("wow2");
			if(!(varType instanceof TYPE_CLASS)){
				System.out.format(">> ERROR [%d] Type mismatch in assignment: %s cannot be assigned to %s\n", line, expType.name, varType.name);
				printError();
			}
			else if(!((TYPE_CLASS)varType).isAncestorOf(expType)){
				System.out.format(">> ERROR [%d] Type mismatch in assignment: %s cannot be assigned to %s\n", line, expType.name, varType.name);
				printError();
			}
		}
	
		/**************************************************/
		/* [4] Return value is irrelevant for assignments */
		/**************************************************/
		return null;
	}
	
}
