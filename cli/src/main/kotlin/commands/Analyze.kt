package commands

import Linter
import Result
import node.ASTNode
import node.ErrorNode
import printScreen.lexer.Lexer
import printScreen.parser.CatchableParser
import java.io.File

class Analyze : CommandExecute {
  override fun execute(vararg file: String): Result {
    val code = TXTHandler.content("/scripts/${file[0]}")
      ?: return Result("File ${file[0]} not found", emptyList())
    val lexer = Lexer()
    val parser = CatchableParser()
    val ast = parser.parse(lexer.lex(code))
    var lastNode: ASTNode? = null
    val configFile = File("cli/src/main/resources/config/${file[1]}")
    if (!configFile.exists()) return Result("Config file ${file[1]} not found", emptyList())
    while (ast.hasNext()) {
      lastNode = ast.next()
      if (lastNode is ErrorNode) break
      val res = Linter.lint(lastNode, configFile)
      if (res.isNotEmpty()) return Result(res.toString(), emptyList())
    }
    if (ast.hasException()) return Result(ast.getException()!!.message!!, emptyList())
    return Result("", listOf("No linter flag has been raised"))
  }
}
