import com.printscript.lexer.Lexer
import com.printscript.parser.PrintParser
import java.io.File

fun main() {
  val lexer = Lexer()
  val parser = PrintParser() // hay que chequear que haya que usar este.
  val test = parser.parse(lexer.lex(File("engine/src/test/resources/test.txt").reader()))
  while (test.hasNext()) {
    println(test.next())
  }
}
