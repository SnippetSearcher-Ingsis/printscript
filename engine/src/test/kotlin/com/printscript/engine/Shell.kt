package com.printscript.engine

import com.printscript.interpreter.Interpreter
import com.printscript.interpreter.input.ConsoleInput
import com.printscript.interpreter.output.ConsoleOutput
import com.printscript.interpreter.strategy.PreConfiguredProviders.VERSION_1_1
import com.printscript.lexer.Lexer
import com.printscript.lexer.util.PreConfiguredTokens.TOKENS_1_1
import com.printscript.parser.PrintParser

fun main() {
  val lexer = Lexer(TOKENS_1_1)
  val parser = PrintParser()
  val input = ConsoleInput()
  val output = ConsoleOutput()
  val interpreter = Interpreter builder {
    this setInput input
    this setOutput output
    this setProvider VERSION_1_1
  }
  output.write("PrintScript v.1.1")
  while (true) {
    try {
      val read = readln()
      val tokens = lexer.lex(read.reader())
      val ast = parser.parse(tokens)
      interpreter.interpret(ast)
    } catch (e: Throwable) {
      output.write(e.message ?: "An error occurred")
    }
  }
}
