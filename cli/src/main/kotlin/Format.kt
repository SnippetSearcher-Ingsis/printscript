import printScreen.lexer.Lexer
import printScreen.parser.Parser
import java.io.File

class Format() : ActionBuilder {
  override fun build(file: File): Result {
    val code = TXTHandler.content("test.txt")
    val tokens = Lexer(code).tokenize()
    val ast = Parser().parse(tokens)
    return Result("", listOf(Formatter().format(ast)))
  }
}
