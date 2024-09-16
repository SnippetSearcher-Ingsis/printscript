package com.printscript.cli.commands

import com.printscript.cli.Result
import com.printscript.cli.TXTHandler
import com.printscript.formatter.Formatter
import com.printscript.formatter.FormatterConfig
import com.printscript.lexer.Lexer
import com.printscript.parser.CatchableParser

class Format : CommandExecute {
  override fun execute(vararg file: String): Result {
    val code = TXTHandler.content("/scripts/${file[0]}")
      ?: return Result("File ${file[0]} not found", listOf())
    val lexer = Lexer()
    val parser = CatchableParser()
    val ast = parser.parse(lexer.lex(code))

    /* val configFile = File("cli/src/main/resources/config/${file[1]}")
    val config = Gson().fromJson(configFile.readText(), FormatterConfig::class.java)
    if (!configFile.exists()) return Result("Config file ${file[1]} not found", listOf()) */
    // bro que es esto la carpeta config ni si quiera existe

    val res: MutableList<String> = mutableListOf()

    while (ast.hasNext()) {
      val node = ast.next()
      if (ast.hasException()) break
      res.add(
        Formatter(
          FormatterConfig(
            0,
            spaceAroundEquals = true,
            noSpaceAroundEquals = null,
            spaceBeforeColon = false,
            spaceAfterColon = true,
            ifBraceSameLine = true,
            ifBraceBelowLine = null,
            indent = 2
          )
        ).format(node)
      )
    }

    if (ast.hasException()) return Result(ast.getException()!!.message!!, emptyList())
    return Result("", res)
  }
}
