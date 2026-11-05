import java.io.{InputStreamReader, BufferedReader, StringReader}

@main def Calc() = {
    val reader = BufferedReader(InputStreamReader(System.in))

    var line = reader.readLine()
    while line != null && line != "" do
        val strReader = StringReader(line)
        val scanner = CalcScanner(strReader)
        while scanner.hasNext do
            val tok = scanner.next()
            print("" + tok + " ")
        line = reader.readLine()

    reader.close()
}
