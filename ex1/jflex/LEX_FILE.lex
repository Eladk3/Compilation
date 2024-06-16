/* JFlex configuration file for lexical scanner */

%%

/* User code section */
%{
  import java.io.*;
  import java.util.*;
  import java_cup.runtime.Symbol;
%}

%class Lexer
%unicode
%cup
%type Symbol
%function yylex
%line
%column

/* Define tokens */
%{
  private void error() {
    System.out.println("ERROR");
    System.exit(1);
  }
%}

/* Regular expressions and token rules */
%%
/* Whitespace */
[ \t\n\r]+                  { /* skip whitespace */ }

/* Comments */
"//"[^\n]*                  { /* skip single line comment */ }
"/*"([^*]|\*+[^*/])*\*+"/"  { /* skip multi-line comment */ }

/* Keywords */
"class"                     { return new Symbol(sym.CLASS); }
"nil"                       { return new Symbol(sym.NIL); }
"array"                     { return new Symbol(sym.ARRAY); }
"while"                     { return new Symbol(sym.WHILE); }
"int"                       { return new Symbol(sym.TYPE_INT); }
"void"                      { return new Symbol(sym.TYPE_VOID); }
"extends"                   { return new Symbol(sym.EXTENDS); }
"return"                    { return new Symbol(sym.RETURN); }
"new"                       { return new Symbol(sym.NEW); }
"if"                        { return new Symbol(sym.IF); }
"string"                    { return new Symbol(sym.TYPE_STRING); }

/* Operators and Delimiters */
"("                         { return new Symbol(sym.LPAREN); }
")"                         { return new Symbol(sym.RPAREN); }
"["                         { return new Symbol(sym.LBRACK); }
"]"                         { return new Symbol(sym.RBRACK); }
"{"                         { return new Symbol(sym.LBRACE); }
"}"                         { return new Symbol(sym.RBRACE); }
"+"                         { return new Symbol(sym.PLUS); }
"-"                         { return new Symbol(sym.MINUS); }
"*"                         { return new Symbol(sym.TIMES); }
"/"                         { return new Symbol(sym.DIVIDE); }
","                         { return new Symbol(sym.COMMA); }
"."                         { return new Symbol(sym.DOT); }
";"                         { return new Symbol(sym.SEMICOLON); }
":="                        { return new Symbol(sym.ASSIGN); }
"="                         { return new Symbol(sym.EQ); }
"<"                         { return new Symbol(sym.LT); }
">"                         { return new Symbol(sym.GT); }

/* Identifiers */
[a-zA-Z][a-zA-Z0-9]*        { return new Symbol(sym.ID, yytext()); }

/* Integers */
0|[1-9][0-9]*               { 
                              try {
                                int value = Integer.parseInt(yytext());
                                if (value >= 0 && value <= 32767) {
                                  return new Symbol(sym.INT, value);
                                } else {
                                  error();
                                }
                              } catch (NumberFormatException e) {
                                error();
                              }
                            }

/* Strings */
\"[a-zA-Z]*\"               { return new Symbol(sym.STRING, yytext()); }

/* Error handling */
.                           { error(); }
