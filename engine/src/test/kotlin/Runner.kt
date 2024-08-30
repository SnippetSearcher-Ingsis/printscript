import printScreen.lexer.Lexer
import printScreen.parser.Parser
import java.io.File

fun main() {
  val si = Parser(Lexer(File("engine/src/test/resources/test.txt").reader()))
  while (si.hasNext()) {
    println(si.next())
  }
}
