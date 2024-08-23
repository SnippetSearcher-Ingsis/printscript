package commands

import Formatter
import Result
import TXTHandler
import printScreen.lexer.Lexer
import printScreen.parser.Parser
import java.io.File

class Format : CommandExecute {
  override fun execute(vararg file: String): Result {
    val code = TXTHandler.content("/scripts/${file[0]}")
    if (code == "") return Result("File ${file[0]} not found", listOf())
    val tokens = Lexer(code).tokenize()
    val ast = Parser().parse(tokens)
    val configFile = File("cli/src/main/resources/config/${file[1]}")
    if (!configFile.exists()) return Result("Config file ${file[1]} not found", listOf())
    return Result("", listOf(Formatter().format(ast, configFile)))
  }
}
