import printScreen.lexer.Lexer
import printScreen.parser.Parser
import java.io.File

fun main() {
  val lexer = Lexer(
    "let x: number=(5-3) *3* 3; x =3;" +
      " println(\"si\"); println(3);"
  )
  val tokens = lexer.tokenize()
  val parser = Parser()
  val ast = parser.parse(tokens)
  val formattedCode = Formatter.format(ast, File("formatter/src/main/resources/formatterConfig.json"))
  println(formattedCode)
}
