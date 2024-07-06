/***************************/
/* FILE NAME: LEX_FILE.lex */
/***************************/

/*************/
/* USER CODE */
/*************/
import java_cup.runtime.*;

/******************************/
/* DOLAR DOLAR - DON'T TOUCH! */
/******************************/

%%

/************************************/
/* OPTIONS AND DECLARATIONS SECTION */
/************************************/
   
/*****************************************************/ 
/* Lexer is the name of the class JFlex will create. */
/* The code will be written to the file Lexer.java.  */
/*****************************************************/ 
%class Lexer

/********************************************************************/
/* The current line number can be accessed with the variable yyline */
/* and the current column number with the variable yycolumn.        */
/********************************************************************/
%line
%column

/*******************************************************************************/
/* Note that this has to be the EXACT same name of the class the CUP generates */
/*******************************************************************************/
%cupsym TokenNames

/******************************************************************/
/* CUP compatibility mode interfaces with a CUP generated parser. */
/******************************************************************/
%cup

/****************/
/* DECLARATIONS */
/****************/
/*****************************************************************************/   
/* Code between %{ and %}, both of which must be at the beginning of a line, */
/* will be copied verbatim (letter to letter) into the Lexer class code.     */
/* Here you declare member variables and functions that are used inside the  */
/* scanner actions.                                                          */  
/*****************************************************************************/   
%{
	/*********************************************************************************/
	/* Create a new java_cup.runtime.Symbol with information about the current token */
	/*********************************************************************************/
	private Symbol symbol(int type)               {return new Symbol(type, yyline, yycolumn);}
	private Symbol symbol(int type, Object value) {return new Symbol(type, yyline, yycolumn, value);}

	/*******************************************/
	/* Enable line number extraction from main */
	/*******************************************/
	public int getLine() { return yyline + 1; } 
	/**********************************************/
	/* Enable token position extraction from main */
	/**********************************************/
	public int getTokenStartPosition() { return yycolumn + 1; } 
%}

/***********************/
/* MACRO DECALARATIONS */
/***********************/
LineTerminator	= \r|\n|\r\n
WhiteSpace		= {LineTerminator} | [ \t]
INTEGER			= [1-9][0-9]* | 0
ID				= [a-zA-Z][a-zA-Z0-9]*
START_COMMENT 	= \/\*
END_COMMENT		= \*\/
LINE_COMMENT 	= "//" [a-zA-Z0-9\t\+\-\*\/\.\;\(\)\[\]\{\}\?\!\s]* {LineTerminator}
STRING  		= \"[a-zA-Z]*\"
COMMENT_CHAR	= [a-zA-Z0-9\t\r\n\+\-\*\/\.\;\(\)\[\]\{\}\s\?\!]
ERROR			= "//" {COMMENT_CHAR}* {STRING} {COMMENT_CHAR}* {LineTerminator}

/******************************/
/* DOLAR DOLAR - DON'T TOUCH! */
/******************************/

%state COMMENT
%state LINE_COMMENT
%state STRING

%%

/************************************************************/
/* LEXER matches regular expressions to actions (Java code) */
/************************************************************/

/**************************************************************/
/* YYINITIAL is the state at which the lexer begins scanning. */
/* So these regular expressions will only be matched if the   */
/* scanner is in the start state YYINITIAL.                   */
/**************************************************************/

<YYINITIAL> {
	"("					{ System.out.println("Matched LPAREN: " + yytext()); return symbol(TokenNames.LPAREN); }
	")"					{ System.out.println("Matched RPAREN: " + yytext()); return symbol(TokenNames.RPAREN); }
	"["					{ System.out.println("Matched LBRACK: " + yytext()); return symbol(TokenNames.LBRACK); }
	"]" 				{ System.out.println("Matched RBRACK: " + yytext()); return symbol(TokenNames.RBRACK); }
	"{"					{ System.out.println("Matched LBRACE: " + yytext()); return symbol(TokenNames.LBRACE); }
	"}"					{ System.out.println("Matched RBRACE: " + yytext()); return symbol(TokenNames.RBRACE); }
	"nil"				{ System.out.println("Matched NIL: " + yytext()); return symbol(TokenNames.NIL); }
	"+"					{ System.out.println("Matched PLUS: " + yytext()); return symbol(TokenNames.PLUS); }
	"-"					{ System.out.println("Matched MINUS: " + yytext()); return symbol(TokenNames.MINUS); }
	"*"					{ System.out.println("Matched TIMES: " + yytext()); return symbol(TokenNames.TIMES); }
	"/"					{ System.out.println("Matched DIVIDE: " + yytext()); return symbol(TokenNames.DIVIDE); }
	","					{ System.out.println("Matched COMMA: " + yytext()); return symbol(TokenNames.COMMA); }
	"."					{ System.out.println("Matched DOT: " + yytext()); return symbol(TokenNames.DOT); }
	";"					{ System.out.println("Matched SEMICOLON: " + yytext()); return symbol(TokenNames.SEMICOLON); }
	"int"				{ System.out.println("Matched TYPE_INT: " + yytext()); return symbol(TokenNames.TYPE_INT); }
	"void"				{ System.out.println("Matched TYPE_VOID: " + yytext()); return symbol(TokenNames.TYPE_VOID); }
	":="				{ System.out.println("Matched ASSIGN: " + yytext()); return symbol(TokenNames.ASSIGN); }
	"="					{ System.out.println("Matched EQ: " + yytext()); return symbol(TokenNames.EQ); }
	"<"					{ System.out.println("Matched LT: " + yytext()); return symbol(TokenNames.LT); }
	">"					{ System.out.println("Matched GT: " + yytext()); return symbol(TokenNames.GT); }
	"class"				{ System.out.println("Matched CLASS: " + yytext()); return symbol(TokenNames.CLASS); }
	"array"				{ System.out.println("Matched ARRAY: " + yytext()); return symbol(TokenNames.ARRAY); }
	"extends"			{ System.out.println("Matched EXTENDS: " + yytext()); return symbol(TokenNames.EXTENDS); }
	"return"			{ System.out.println("Matched RETURN: " + yytext()); return symbol(TokenNames.RETURN); }
	"while"				{ System.out.println("Matched WHILE: " + yytext()); return symbol(TokenNames.WHILE); }
	"if"				{ System.out.println("Matched IF: " + yytext()); return symbol(TokenNames.IF); }
	"new"				{ System.out.println("Matched NEW: " + yytext()); return symbol(TokenNames.NEW); }
	"string"			{ System.out.println("Matched TYPE_STRING: " + yytext()); return symbol(TokenNames.TYPE_STRING); }
	{INTEGER}			{ System.out.println("Matched INT: " + yytext()); return symbol(TokenNames.INT, new String(yytext())); }
	{ID}				{ System.out.println("Matched ID: " + yytext()); return symbol(TokenNames.ID, new String(yytext())); }  
	{LineTerminator}	{ System.out.println("Skipped LineTerminator: " + yytext()); } 
	{WhiteSpace}		{ System.out.println("Skipped WhiteSpace: " + yytext()); /* just skip what was found, do nothing */ }
	{LINE_COMMENT}		{ System.out.println("Matched LINE_COMMENT: " + yytext()); yybegin(LINE_COMMENT); }
	{START_COMMENT}		{ System.out.println("Matched START_COMMENT: " + yytext()); yybegin(COMMENT); }
	{STRING}			{ System.out.println("Matched STRING: " + yytext()); return symbol(TokenNames.STRING, new String(yytext())); }
	<<EOF>>				{ System.out.println("Matched EOF"); return symbol(TokenNames.EOF); }
}

<LINE_COMMENT> {
    {LineTerminator}	{ System.out.println("Matched LineTerminator in COMMENT: " + yytext()); yybegin(YYINITIAL); }
    .					{ /* skip comment text */ }
}

<COMMENT> {
	"*"					{ System.out.println("Inside COMMENT: *"); }
	{COMMENT_CHAR}		{ System.out.println("Inside COMMENT: " + yytext()); }
	"*/"				{ System.out.println("Matched END_COMMENT: " + yytext()); yybegin(YYINITIAL); }
}

{ERROR}					{ System.out.println("Matched ERROR: " + yytext()); return symbol(TokenNames.ERROR, new String(yytext())); }
.						{ System.out.println("Matched ERROR (default): " + yytext()); return symbol(TokenNames.ERROR, new String(yytext())); }
