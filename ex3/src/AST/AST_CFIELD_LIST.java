package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_CFIELD_LIST extends AST_Node {

    public AST_CFIELD cField;
    public AST_CFIELD_LIST cFieldList;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_CFIELD_LIST(AST_CFIELD cField, AST_CFIELD_LIST cFieldList, int lineNum) {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        if (cFieldList != null)
            System.out.println("====================== cFieldList -> cField cFieldList");
        else
            System.out.println("====================== cFieldList -> cField");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.cField = cField;
        this.cFieldList = cFieldList;
    }

    /***************************************************/
    /* The printing message for a class field list AST node */
    /***************************************************/
    public void PrintMe() {
        /*********************************/
        /* AST NODE TYPE = CLASS FIELD LIST */
        /*********************************/
        System.out.println("AST NODE CLASS FIELD LIST");

        /******************************************/
        /* RECURSIVELY PRINT cField and cFieldList */
        /******************************************/
        if (cField != null) cField.PrintMe();
        if (cFieldList != null) cFieldList.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "CLASS FIELD LIST");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (cField != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, cField.SerialNumber);
        if (cFieldList != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, cFieldList.SerialNumber);
    }

    public TYPE_LIST SemantMe() {
        /***************************************/
        /* [1] Semantically analyze the type of the cField */
        /***************************************/
        TYPE t = cField.SemantMe();
    
        /***************************************/
        /* [3] Semantically analyze the rest of the cFields */
        /***************************************/
        if (cFieldList == null)
		{
			return new TYPE_LIST(
				t,
				null);
		}
		else
		{
			return new TYPE_LIST(
				t,
				cFieldList.SemantMe());
		}
    }
}
