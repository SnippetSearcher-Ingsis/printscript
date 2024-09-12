package com.printscript.engine

import com.printscript.formatter.Formatter
import com.printscript.lexer.Lexer
import com.printscript.parser.PrintParser
import java.io.File

fun main() {
  val lexer = Lexer()
  val parser = PrintParser()
  val test = parser.parse(lexer.lex(File("engine/src/test/resources/test.txt").reader()))
  while (test.hasNext()) {
    val node = test.next()
    println(Formatter.format(node, File("engine/src/test/resources/formatterConfig.json")))
  }
}
