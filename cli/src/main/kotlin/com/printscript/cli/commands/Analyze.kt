package com.printscript.cli.commands

import com.google.gson.Gson
import com.printscript.cli.Result
import com.printscript.cli.TXTHandler
import com.printscript.lexer.Lexer
import com.printscript.linter.Linter
import com.printscript.linter.LinterConfig
import com.printscript.linter.violation.Violation
import com.printscript.parser.CatchableParser
import java.io.File

class Analyze : CommandExecute {
  override fun execute(vararg file: String): Result {
    val code = TXTHandler.content("/scripts/${file[0]}")
      ?: return Result("File ${file[0]} not found", emptyList())
    val lexer = Lexer()
    val parser = CatchableParser()
    val ast = parser.parse(lexer.lex(code))

    val configFile = File("cli/src/main/resources/config/${file[1]}")
    if (!configFile.exists()) return Result("Config file ${file[1]} not found", emptyList())

    val config = Gson().fromJson(configFile.readText(), LinterConfig::class.java)
    val res: MutableList<Violation> = mutableListOf()

    while (ast.hasNext()) {
      val node = ast.next()
      if (ast.hasException()) break

      val violations = Linter(config).lint(node)
      if (violations.isNotEmpty()) {
        res.addAll(violations)
      }
    }

    if (ast.hasException()) return Result(ast.getException()!!.message!!, emptyList())

    if (res.isNotEmpty()) {
      return Result(res.map { it.toString() }.toString(), emptyList())
    }

    return Result("", listOf("No linter flag has been raised"))
  }
}
