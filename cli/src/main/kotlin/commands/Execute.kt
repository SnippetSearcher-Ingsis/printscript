package commands

import CatchableTracingInterpreter
import Result
import TXTHandler
import printScreen.lexer.Lexer
import printScreen.parser.CatchableParser

class Execute : CommandExecute {
  override fun execute(vararg file: String): Result {
    val code = TXTHandler.content("/scripts/${file[0]}")
    if (code == "") return Result("File ${file[0]} not found", listOf())
    val tokens = Lexer(code).tokenize()
    val parser = CatchableParser()
    val ast = parser.parse(tokens)
    return if (parser.hasException()) Result(parser.getException()!!.message!!, listOf()) else {
      val interpreter = CatchableTracingInterpreter(print = false)
      interpreter.interpret(ast)
      if (interpreter.hasException()) Result(interpreter.getException()!!.message!!, listOf()) else Result(
        "",
        interpreter.getLog()
      )
    }
  }
}
