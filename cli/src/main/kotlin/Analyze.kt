import printScreen.lexer.Lexer
import printScreen.parser.CatchableParser
import java.io.File

class Analyze() : ActionBuilder {
  override fun build(file: String): Result {
    val code = TXTHandler.content(file)
    val tokens = Lexer(code).tokenize()
    val parser = CatchableParser()
    val ast = parser.parse(tokens)
    return if (parser.hasException()) Result(parser.getException()!!.message!!, listOf()) else {
      val res = Linter().lint(ast)
      if (res.isEmpty()) Result("", listOf()) else Result(res.toString(), listOf())
    }
  }
}
