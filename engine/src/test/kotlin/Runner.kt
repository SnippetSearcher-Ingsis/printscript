import printScreen.lexer.Lexer
import printScreen.parser.Parser
import java.io.File

fun main() {
  val lexer = Lexer()
  val parser = Parser()
  val test = lexer.lex(File("engine/src/test/resources/test.txt").reader())
  Interpreter().interpret(parser.ParserIterator(test))
  while (test.hasNext()) {
    val tokens = test.next()
    println(tokens)
  }
}
