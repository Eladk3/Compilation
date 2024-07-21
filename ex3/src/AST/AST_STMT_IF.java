package AST;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TYPES.*;
import SYMBOL_TABLE.*;

public class AST_STMT_IF extends AST_STMT
{
    public AST_EXP cond;
    public AST_STMT_LIST body;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AST_STMT_IF(AST_EXP cond, AST_STMT_LIST body, int lineNum)
    {
        super(lineNum);
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        SerialNumber = AST_Node_Serial_Number.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.println("====================== stmt -> IF LPAREN exp RPAREN LBRACE stmtList RBRACE");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.cond = cond;
        this.body = body;
    }

    /***************************************************/
    /* The printing message for an if statement AST node */
    /***************************************************/
    public void PrintMe()
    {
        /*********************************/
        /* AST NODE TYPE = IF STATEMENT */
        /*********************************/
        System.out.println("AST NODE IF STATEMENT");

        /******************************************/
        /* RECURSIVELY PRINT cond and body ... */
        /******************************************/
        if (cond != null) cond.PrintMe();
        if (body != null) body.PrintMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AST_GRAPHVIZ.getInstance().logNode(
            SerialNumber,
            "IF STATEMENT");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (cond != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, cond.SerialNumber);
        if (body != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, body.SerialNumber);
    }

    public TYPE SemantMe() {
        /*********************************/
        /* [1] Recursively semant cond */
        /*********************************/
        if(cond == null){
            System.out.println(">> ERROR: Condition expression missing in if statement");
            printError();
            return null;
        }
        TYPE condType = cond.SemantMe();

        /*************************************/
        /* [2] Check that condition is BOOL */
        /*************************************/
        if (!(condType instanceof TYPE_INT)) {
            System.out.format(">> ERROR [%d] condition inside while loop must be of type int\n", line);
            printError();
        }
    
        /*************************************/
        /* [3] Enter a new scope */
        /*************************************/
        SYMBOL_TABLE.getInstance().beginScope();

        /*******************************/
        /* [4] Recursively semant body */
        /*******************************/
        if(body != null){

            body.SemantMe();
        }

        /****************************/
        /* [5] Exit the current scope */
        /****************************/
        SYMBOL_TABLE.getInstance().endScope();
    
        /**************************************************/
        /* [6] Return value is irrelevant for statements */
        /**************************************************/
        return null;
    }
    
}
