import printScreen.lexer.Lexer
import printScreen.parser.CatchableParser

fun main() {
  val interpreter = CatchableTracingInterpreter()
  while (true) {
    val input = readln()
    if (input == "exit()" || input == "exit();") break
    val lexer = Lexer(input)
    try {
      val tokens = lexer.tokenize()
      val parser = CatchableParser()
      val ast = parser.parse(tokens)
      when {
        parser.hasException() -> {
          println(parser.getException())
          continue
        }

        else -> {
          interpreter.interpret(ast)
          if (interpreter.hasException()) println(interpreter.getException())
        }
      }
    } catch (e: Exception) {
      println(e)
    }
  }
}
