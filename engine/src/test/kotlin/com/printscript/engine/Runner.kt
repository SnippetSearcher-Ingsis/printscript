package com.printscript.engine

import com.printscript.interpreter.Interpreter
import printScreen.lexer.com.printscript.lexer.Lexer
import printScreen.parser.com.printscript.parser.Parser
import java.io.File

fun main() {
  val lexer = Lexer()
  val parser = Parser()
  val test = lexer.lex(File("engine/src/test/resources/test.txt").reader())
  Interpreter().interpret(parser.ParserIterator(test))
}
