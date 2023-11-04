import java.io.InputStreamReader

@main def Calc() = {
    val reader = InputStreamReader(System.in)
    val scanner = CalcScanner(reader)
    while scanner.hasNext do
        val tok = scanner.next()
        println(tok)
}