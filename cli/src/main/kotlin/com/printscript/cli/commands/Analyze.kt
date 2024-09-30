package com.printscript.cli.commands

import com.google.gson.Gson
import com.printscript.cli.Result
import com.printscript.cli.TXTHandler
import com.printscript.lexer.Lexer
import com.printscript.lexer.util.PreConfiguredTokens.TOKENS_1_1
import com.printscript.linter.Linter
import com.printscript.linter.LinterConfig
import com.printscript.linter.violation.Violation
import com.printscript.parser.CatchableParser
import java.io.File
import java.io.InputStreamReader

class Analyze : CommandExecute {
  override fun execute(vararg file: String): Result {
    val code = TXTHandler.content("/scripts/${file[0]}")
      ?: return Result("File ${file[0]} not found", emptyList())
    return result(code, file)
  }

  private fun result(code: InputStreamReader, file: Array<out String>): Result {
    val lexer = Lexer(TOKENS_1_1)
    val parser = CatchableParser()
    val ast = parser.parse(lexer.lex(code))

    val configFile = File("cli/src/main/resources/config/${file[1]}")
    if (!configFile.exists()) return Result("Config file ${file[1]} not found", emptyList())

    val config = Gson().fromJson(configFile.readText(), LinterConfig::class.java)
    val res: MutableList<Violation> = mutableListOf()

    val violations = Linter(config).lint(ast)
    if (violations.isNotEmpty()) {
      res.addAll(violations)
      }

    return when {
      ast.hasException() -> Result(ast.getException()!!.message!!, emptyList())
      res.isNotEmpty() -> Result(res.map { it.toString() }.toString(), emptyList())
      else -> Result("", listOf("No linter flag has been raised"))
      }
  }
}
