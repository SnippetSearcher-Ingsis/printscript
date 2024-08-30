
import printScreen.lexer.Lexer
import printScreen.parser.Parser
import java.io.File

fun main() {
  val lexer = Lexer()
  val parser = Parser()
  val interpreter = Interpreter()
  interpreter.interpret(parser.parse(lexer.lex(File("engine/src/test/resources/test.txt").reader())))
}
