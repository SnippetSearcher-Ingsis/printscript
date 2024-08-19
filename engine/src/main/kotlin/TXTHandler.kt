import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object TXTHandler {
    fun content(filename: String): String {
        val inputStream = TXTHandler::class.java.getResourceAsStream(filename)
        if (inputStream != null) {
            try {
                BufferedReader(InputStreamReader(inputStream)).use { reader ->
                    val stringBuilder = StringBuilder()
                    var line: String?
                    while ((reader.readLine().also { line = it }) != null) {
                        stringBuilder.append(line).append("\n")
                    }
                    return stringBuilder.toString()
                }
            } catch (e: IOException) {
                println("Error reading file: " + e.message)
            }
        }
        return ""
    }
}
