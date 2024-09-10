package com.printscript.cli.commands

import com.google.gson.Gson
import com.printscript.cli.Result
import com.printscript.cli.TXTHandler
import com.printscript.lexer.Lexer
import com.printscript.linter.Linter
import com.printscript.linter.LinterConfig
import com.printscript.parser.CatchableParser
import java.io.File

class Analyze : CommandExecute {
  override fun execute(vararg file: String): Result {
    val code = TXTHandler.content("/scripts/${file[0]}")
      ?: return Result("File ${file[0]} not found", emptyList())
    val lexer = Lexer()
    val parser = CatchableParser()
    val ast = parser.parse(lexer.lex(code))
    var lastNode: com.printscript.models.node.ASTNode?
    val configFile = File("cli/src/main/resources/config/${file[1]}")
    if (!configFile.exists()) return Result("Config file ${file[1]} not found", emptyList())
    while (ast.hasNext()) {
      lastNode = ast.next()
      if (lastNode is com.printscript.models.node.ErrorNode) break
      val gson = Gson()
      val config = gson.fromJson(configFile.readText(), LinterConfig::class.java)
      val res = Linter.lint(lastNode, config)
      if (res.isNotEmpty()) return Result(res.toString(), emptyList())
    }
    if (ast.hasException()) return Result(ast.getException()!!.message!!, emptyList())
    return Result("", listOf("No linter flag has been raised"))
  }
}
