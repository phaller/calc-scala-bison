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

%type<Int> expr

%%

  /* Grammar */

program: expr {
    result = $1
}
;

expr: expr PLUS expr {
    $$ = $1 + $3
}
| expr MINUS expr {
    $$ = $1 - $3
}
| expr TIMES expr {
    $$ = $1 * $3
}
| expr DIV expr {
    $$ = $1 / $3
}
| NUM {
    nums = $1 :: nums
    $$ = $1
}
| LPAREN expr RPAREN {
    $$ = $2
}
;
%%
  def yyerror(msg: String) = { };
  var result: Int = 0;
  var nums = List[Int]();
