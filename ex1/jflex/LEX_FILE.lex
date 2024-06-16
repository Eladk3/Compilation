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
/* MACRO DECLARATIONS */
/***********************/
LineTerminator  = \r|\n|\r\n
WhiteSpace      = {LineTerminator} | [ \t]
INTEGER         = 0 | [1-9][0-9]*
ID              = [a-zA-Z][a-zA-Z0-9]*

/* Keywords */
CLASS           = "class"
NIL             = "nil"
ARRAY           = "array"
WHILE           = "while"
INT             = "int"
VOID            = "void"
EXTENDS         = "extends"
RETURN          = "return"
NEW             = "new"
IF              = "if"
STRING          = "string"

/******************************/
/* DOLAR DOLAR - DON'T TOUCH! */
/******************************/

%%

/************************************************************/
/* LEXER matches regular expressions to actions (Java code) */
/************************************************************/

/**************************************************************/
/* YYINITIAL is the state at which the lexer begins scanning. */
/* So these regular expressions will only be matched if the   */
/* scanner is in the start state YYINITIAL.                   */
/**************************************************************/

/* Add the COMMENT state declaration */
%state COMMENT

<YYINITIAL> {

{WhiteSpace}            { /* just skip what was found, do nothing */ }

"+"                     { return symbol(TokenNames.PLUS); }
"-"                     { return symbol(TokenNames.MINUS); }
"*"                     { return symbol(TokenNames.TIMES); }
"/"                     { return symbol(TokenNames.DIVIDE); }
"("                     { return symbol(TokenNames.LPAREN); }
")"                     { return symbol(TokenNames.RPAREN); }
"["                     { return symbol(TokenNames.LBRACK); }
"]"                     { return symbol(TokenNames.RBRACK); }
"{"                     { return symbol(TokenNames.LBRACE); }
"}"                     { return symbol(TokenNames.RBRACE); }
","                     { return symbol(TokenNames.COMMA); }
"."                     { return symbol(TokenNames.DOT); }
";"                     { return symbol(TokenNames.SEMICOLON); }
"="                     { return symbol(TokenNames.EQ); }
"<"                     { return symbol(TokenNames.LT); }
">"                     { return symbol(TokenNames.GT); }
":="                    { return symbol(TokenNames.ASSIGN); }

{CLASS}                 { return symbol(TokenNames.CLASS); }
{NIL}                   { return symbol(TokenNames.NIL); }
{ARRAY}                 { return symbol(TokenNames.ARRAY); }
{WHILE}                 { return symbol(TokenNames.WHILE); }
{INT}                   { return symbol(TokenNames.TYPE_INT); }
{VOID}                  { return symbol(TokenNames.TYPE_VOID); }
{EXTENDS}               { return symbol(TokenNames.EXTENDS); }
{RETURN}                { return symbol(TokenNames.RETURN); }
{NEW}                   { return symbol(TokenNames.NEW); }
{IF}                    { return symbol(TokenNames.IF); }
{STRING}                { return symbol(TokenNames.TYPE_STRING); }

{INTEGER}               { return symbol(TokenNames.INT, new Integer(yytext())); }
/\"([^\"\n]*)\"         { return symbol(TokenNames.STRING, yytext()); } // Properly escape quotes
{ID}                    { return symbol(TokenNames.ID, yytext()); }

"//".*                  { /* skip single-line comments */ }
"/*"                    { yybegin(COMMENT); }
}

<COMMENT> {
[^*]*                  { /* ignore comment content */ }
"*"[^/]                { /* ignore comment content */ }
"*"/                   { yybegin(YYINITIAL); }
<<EOF>>                { return symbol(TokenNames.ERROR); }
}

<<EOF>>                 { return symbol(TokenNames.EOF); }
