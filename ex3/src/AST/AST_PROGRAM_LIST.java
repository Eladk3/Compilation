package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_PROGRAM_LIST extends AST_PROGRAM {

    /****************/
	/* DATA MEMBERS */
	/****************/
    public AST_DECLARATION dec;
    public AST_PROGRAM prog;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_PROGRAM_LIST(AST_DECLARATION dec, AST_PROGRAM prog, int lineNum)
    {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        if(prog == null) System.out.println("====================== program -> dec      \n");
        else System.out.println("====================== program -> dec program");

        /*******************************/
        /* COPY INPUT DATA NENBERS ... */
        /*******************************/
        this.dec = dec;
        this.prog = prog;
    }

    /***************************************************/
    /* The printing message for a program list AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = PROGRAM LIST */
        /*********************************/
        System.out.println("AST NODE PROGRAM LIST");

        /******************************************/
        /* RECURSIVELY PRINT dec, then prog ... */
        /******************************************/
        if (dec != null) dec.PrintMe();
        if (prog != null) prog.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "PROGRAM\nLIST");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (dec != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, dec.SerialNumber);
        if (prog != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, prog.SerialNumber);
    }

    public TYPE SemantMe() {
        /***********************************************/
        /* [1] Semantically analyze the declaration (dec) */
        /***********************************************/
        if(dec != null){

            dec.SemantMe();
        }
    
        /***********************************************/
        /* [2] Semantically analyze the rest of the program (prog) */
        /***********************************************/
        if (prog != null) {
            prog.SemantMe();
        }
    
        /**************************************************/
        /* [3] Return value is irrelevant for program lists */
        /**************************************************/
        return null;
    }
    
}
