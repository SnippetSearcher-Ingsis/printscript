package com.printscript.engine.integral

import com.printscript.interpreter.Interpreter
import com.printscript.interpreter.input.ConsoleInput
import com.printscript.interpreter.output.ReadableOutput
import com.printscript.interpreter.strategy.PreConfiguredProviders.VERSION_1_1
import com.printscript.lexer.Lexer
import com.printscript.lexer.util.PreConfiguredTokens.TOKENS_1_1
import com.printscript.parser.PrintParser
import kotlin.jvm.Throws

class Tester(private val name: String) {
  @Throws(Exception::class)
  fun test() {
    val loader = Loader(name)
    val lexer = Lexer(TOKENS_1_1)
    val tokens = lexer.lex(loader.loadInput())
    val parser = PrintParser()
    val ast = parser.parse(tokens)
    val output = ReadableOutput()
    val interpreter = Interpreter builder {
      this setInput ConsoleInput()
      this setOutput output
      this setProvider VERSION_1_1
    }
    interpreter.interpret(ast.iterator())
    assert(output.getOutput() == loader.loadOutput())
  }
}
