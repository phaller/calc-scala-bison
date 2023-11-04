import java.io.Reader
import CalcTokens._

class CalcScanner(reader: Reader) extends Iterator[YYToken] {

    private var currentChar: Option[Char] = None
    private var currentToken: Option[YYToken] = None

    // initialize `currentChar`
    nextChar()

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

    def nextChar(): Unit =
        val charCode: Int = reader.read()
        if charCode == -1 then currentChar = None else currentChar = Some(charCode.toChar)

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
            else if char == '(' then
                nextChar()
                LPAREN()
            else if char == ')' then
                nextChar()
                RPAREN()
            // 0+4, 056 -> NUM(0) NUM(56)
            else if char == '0' then
                nextChar()
                NUM(0)
            // 405, 56
            else if char.toInt > 48 && char.toInt <= 57 then
                var k = 0
                while currentChar.nonEmpty && currentChar.get.toInt >= 48 && currentChar.get.toInt <= 57 do
                    k = 10 * k + ("" + currentChar.get).toInt
                    nextChar()
                NUM(k)
            else if char == ' ' || char == '\t' then
                nextChar()
                nextToken()
            else
                nextChar()
                YYCHAR(char)
    }
}
