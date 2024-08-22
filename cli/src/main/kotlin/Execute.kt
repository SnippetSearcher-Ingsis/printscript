import printScreen.lexer.Lexer
import printScreen.parser.CatchableParser
import printScreen.parser.Parser
import java.io.File

class Execute() : ActionBuilder {
  override fun build(file: String): Result {
    val code = TXTHandler.content(file)
    val tokens = Lexer(code).tokenize()
    val parser = CatchableParser()
    val ast = parser.parse(tokens)
    return if (parser.hasException())  Result(parser.getException()!!.message!!, listOf()) else {
      val interpreter = CatchableTracingInterpreter()
      interpreter.interpret(ast)
      if (interpreter.hasException()) Result(interpreter.getException()!!.message!!, listOf()) else Result("", interpreter.getLog())
    }
  }
}
