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

%left PLUS MINUS
%left TIMES DIV

%type<Expr> expr

%%

  /* Grammar */

program: expr {
    tree = $1
}
;

expr: expr PLUS expr {
    $$ = EBinOp(CalcTokens.PLUS(), $1, $3)
}
| expr MINUS expr {
    $$ = EBinOp(CalcTokens.MINUS(), $1, $3)
}
| expr TIMES expr {
    $$ = EBinOp(CalcTokens.TIMES(), $1, $3)
}
| expr DIV expr {
    $$ = EBinOp(CalcTokens.DIV(), $1, $3)
}
| NUM {
    $$ = ENum($1)
}
| LPAREN expr RPAREN {
    $$ = $2
}
;
%%
  def yyerror(msg: String) = { };
  var tree: Expr = null;
