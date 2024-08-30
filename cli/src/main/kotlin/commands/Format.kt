package commands

import Result
import node.ASTNode
import node.ErrorNode
import printScreen.lexer.Lexer
import printScreen.parser.CatchableParser
import java.io.File

class Format : CommandExecute {
  override fun execute(vararg file: String): Result {
    val code = TXTHandler.content("/scripts/${file[0]}")
      ?: return Result("File ${file[0]} not found", listOf())
    val lexer = Lexer()
    val parser = CatchableParser()
    val ast = parser.parse(lexer.lex(code))
    var lastNode: ASTNode? = null
    val configFile = File("cli/src/main/resources/config/${file[1]}")
    if (!configFile.exists()) return Result("Config file ${file[1]} not found", listOf())
    val res: MutableList<String> = mutableListOf()
    while (ast.hasNext()) {
      lastNode = ast.next()
      if (lastNode is ErrorNode) break
      res.add(Formatter.format(lastNode, configFile))
    }
    if (ast.hasException()) return Result(ast.getException()!!.message!!, emptyList())
    return Result("", res)
  }
}
