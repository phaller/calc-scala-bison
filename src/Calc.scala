import java.io.{InputStreamReader, BufferedReader, StringReader}

@main def Calc() = {
    val reader = BufferedReader(InputStreamReader(System.in))

    var line = reader.readLine()
    while line != null && line != "" do
        val strReader = StringReader(line)
        val scanner = CalcScanner(strReader)

        val parser = CalcParser()
        parser.yyreset(scanner)
        val success = parser.yyparse()
        if success then
            println("Success")
            println(s"Result: ${parser.result}")
        else
            println("Error")
        line = reader.readLine()

    reader.close()
}
