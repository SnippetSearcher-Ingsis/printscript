package com.printscript.engine

import com.google.gson.Gson
import com.printscript.formatter.Formatter
import com.printscript.formatter.FormatterConfig
import com.printscript.lexer.Lexer
import com.printscript.lexer.util.PreConfiguredTokens.TOKENS_1_1
import com.printscript.parser.PrintParser
import java.io.File

fun main() {
  val lexer = Lexer(TOKENS_1_1)
  val parser = PrintParser()
  val test = parser.parse(lexer.lex(File("engine/src/test/resources/test.txt").reader()))
  val configFile = File("engine/src/test/resources/formatterConfig.json")
  val config = Gson().fromJson(configFile.readText(), FormatterConfig::class.java)
  println(Formatter(config).format(test))
}
