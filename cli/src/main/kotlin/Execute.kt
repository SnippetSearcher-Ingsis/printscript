import printScreen.lexer.Lexer
import printScreen.parser.Parser
import java.io.File

class Execute() : ActionBuilder {
  override fun build(file: File): Result {
    val code = TXTHandler.content("test.txt")
    val tokens = Lexer(code).tokenize()
    val asts = Parser().parse(tokens)
    val interpreter = TracingInterpreter()
    interpreter.interpret(asts)
    val result = interpreter.getLog()
    return Result("", result)
  }
}
