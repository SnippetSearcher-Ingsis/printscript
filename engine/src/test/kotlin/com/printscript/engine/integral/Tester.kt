package com.printscript.engine.integral

import com.printscript.interpreter.Interpreter
import com.printscript.interpreter.input.ConsoleInput
import com.printscript.interpreter.output.ReadableOutput
import com.printscript.interpreter.strategy.PreConfiguredProviders.VERSION_1_1
import com.printscript.lexer.Lexer
import com.printscript.parser.PrintParser
import kotlin.jvm.Throws

class Tester(private val name: String) {
  @Throws(Exception::class)
  fun test() {
    val loader = Loader(name)
    val lexer = Lexer()
    val tokens = lexer.lex(loader.loadInput())
    val parser = PrintParser()
    val ast = parser.parse(tokens)
    val output = ReadableOutput()
    val input = ConsoleInput()
    val interpreter = Interpreter builder {
      add input input
      add output output
      add provider VERSION_1_1
    }
    interpreter.interpret(ast.iterator())
    assert(output.getOutput() == loader.loadOutput())
  }
}
