trait Expr
case class EBinOp(op: CalcTokens.YYToken, left: Expr, right: Expr) extends Expr
case class ENum(value: Int) extends Expr
