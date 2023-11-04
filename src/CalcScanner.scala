import CalcTokens._
import java.io.Reader

class CalcScanner(reader: Reader) extends Iterator[YYToken] {

    private var currentChar: Option[Char] = None
    private var currentToken: Option[YYToken] = None

    // initialize `currentChar`
    nextChar()

    def nextChar(): Unit =
        val charCode: Int = reader.read()
        currentChar =
            if charCode == -1 then None else Some(charCode.toChar)

    def hasNext: Boolean =
        if currentToken.isEmpty then currentToken = Some(nextToken())
        currentToken != Some(YYEOF())

    def next(): YYToken =
        currentToken match {
            case None =>
                val tok = nextToken()
                currentToken = Some(tok)
                tok
            case Some(tok) =>
                currentToken = Some(nextToken())
                tok
        }

    def nextToken(): YYToken = currentChar match {
        case None => YYEOF()
        case Some(char) =>
            if char == '+' then
                nextChar()
                PLUS()
            else if char == '-' then
                nextChar()
                MINUS()
            else if char == '*' then
                nextChar()
                TIMES()
            else if char == '/' then
                nextChar()
                DIV()
            else if char == '0' then
                nextChar()
                NUM(0)
            else if Character.isDigit(char) then
                var k = 0
                while currentChar.nonEmpty && Character.isDigit(currentChar.get) do
                    k = 10 * k + ("" + currentChar.get).toInt
                    nextChar()
                NUM(k)
            else if char == '(' then
                nextChar()
                LPAREN()
            else if char == ')' then
                nextChar()
                RPAREN()
            else if char == ' ' || char == '\t' then
                nextChar()
                nextToken()
            else
                nextChar()
                YYCHAR(char)
    }

}
