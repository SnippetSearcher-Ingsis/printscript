package com.printscript.cli.commands

import com.printscript.cli.Result
import com.printscript.cli.TXTHandler
import com.printscript.interpreter.CatchableInterpreter
import com.printscript.interpreter.TracingInterpreter
import com.printscript.interpreter.tracer.ReadableTracer
import com.printscript.lexer.Lexer
import com.printscript.parser.CatchableParser
class Execute : CommandExecute {
  override fun execute(vararg file: String): Result {
    val code = TXTHandler.content("/scripts/${file[0]}")
      ?: return Result("File ${file[0]} not found", listOf())
    val lexer = Lexer()
    val parser = CatchableParser()
    val ast = parser.parse(lexer.lex(code))
    val tracer = ReadableTracer()
    val interpreter = CatchableInterpreter(TracingInterpreter(tracer))
    interpreter.interpret(ast)
    return if (interpreter.hasException()) {
      Result(interpreter.getException()!!.message!!, listOf())
    } else {
      Result("", tracer.getOutput())
    }
  }
}
