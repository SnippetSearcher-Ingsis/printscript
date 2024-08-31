package commands

import CatchableTracingInterpreter
import Result
import printScreen.lexer.Lexer
import printScreen.parser.CatchableParser

class Execute : CommandExecute {
  override fun execute(vararg file: String): Result {
    val code = TXTHandler.content("/scripts/${file[0]}")
      ?: return Result("File ${file[0]} not found", listOf())
    val lexer = Lexer()
    val parser = CatchableParser()
    val ast = parser.parse(lexer.lex(code))
    val interpreter = CatchableTracingInterpreter(print = false)
    interpreter.interpret(ast)
    return if (interpreter.hasException()) {
      Result(interpreter.getException()!!.message!!, listOf())
    } else {
      Result("", interpreter.getLog())
    }
  }
}
