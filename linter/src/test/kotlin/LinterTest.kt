import printScreen.lexer.Lexer
import printScreen.parser.Parser
import java.io.File

fun main() {
  val lexer = Lexer(
    "println(1+1); \n" +
      "let a_a: number = 1 + (2 * 3);"
  )
  val tokens = lexer.tokenize()
  val parser = Parser()
  val ast = parser.parse(tokens)
  val violations = Linter.lint(ast, File("linter/src/main/resources/linterConfig.json"))
  print(violations)
}
