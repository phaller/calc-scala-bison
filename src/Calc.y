/*
Calc grammar:

expr -> expr "+" expr
| expr "-" expr
| expr "*" expr
| expr "/" expr
| Num
| "(" expr ")"
*/

%token PLUS
%token MINUS
%token TIMES
%token DIV
%token<Int> NUM
%token LPAREN
%token RPAREN

%%

/* Grammar */

program:
;

%%
  def yyerror(msg: String): Unit = { }
