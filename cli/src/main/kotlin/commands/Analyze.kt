package commands

import Linter
import Result
import TXTHandler
import printScreen.lexer.Lexer
import printScreen.parser.CatchableParser
import java.io.File

class Analyze : CommandExecute {
  override fun execute(vararg file: String): Result {
    val code = TXTHandler.content("/scripts/${file[0]}")
    if (code == "") return Result("File ${file[0]} not found", emptyList())
    val tokens = Lexer(code).tokenize()
    val parser = CatchableParser()
    val ast = parser.parse(tokens)
    return if (parser.hasException()) Result(parser.getException()!!.message!!, emptyList()) else {
      val configFile = File("cli/src/main/resources/config/${file[1]}")
      if (!configFile.exists()) return Result("Config file ${file[1]} not found", listOf())
      val res = Linter.lint(ast, configFile)
      if (res.isEmpty()) Result("", emptyList()) else Result(res.toString(), emptyList())
    }
  }
}
