package com.printscript.cli.commands

import com.printscript.cli.Result
import com.printscript.cli.TXTHandler
import com.printscript.interpreter.CatchableInterpreter
import com.printscript.interpreter.Interpreter
import com.printscript.interpreter.input.ConsoleInput
import com.printscript.interpreter.output.ReadableOutput
import com.printscript.interpreter.strategy.PreConfiguredProviders.VERSION_1_1
import com.printscript.lexer.Lexer
import com.printscript.parser.CatchableParser
class Execute : CommandExecute {
  override fun execute(vararg file: String): Result {
    val code = TXTHandler.content("/scripts/${file[0]}")
      ?: return Result("File ${file[0]} not found", listOf())
    val lexer = Lexer()
    val parser = CatchableParser()
    val ast = parser.parse(lexer.lex(code))
    val output = ReadableOutput()
    val input = ConsoleInput()
    val interpreter = CatchableInterpreter(
      Interpreter builder {
        add input input
        add output output
        add provider VERSION_1_1
      }
    )
    interpreter.interpret(ast)
    return if (interpreter.hasException()) {
      Result(interpreter.getException()!!.message!!, listOf())
    } else {
      Result("", output.getOutput())
    }
  }
}
