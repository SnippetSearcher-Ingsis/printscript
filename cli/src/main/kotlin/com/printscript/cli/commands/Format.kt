package com.printscript.cli.commands

import com.printscript.cli.Result
import com.printscript.cli.TXTHandler
import com.printscript.formatter.Formatter
import com.printscript.formatter.FormatterConfig
import com.printscript.lexer.Lexer
import com.printscript.lexer.util.PreConfiguredTokens.TOKENS_1_1
import com.printscript.parser.CatchableParser

class Format : CommandExecute {
  override fun execute(vararg file: String): Result {
    val code = TXTHandler.content("/scripts/${file[0]}")
      ?: return Result("File ${file[0]} not found", listOf())
    val lexer = Lexer(TOKENS_1_1)
    val parser = CatchableParser()
    val ast = parser.parse(lexer.lex(code))

    val res: MutableList<String> = mutableListOf()
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
      ).format(ast)
    )

    if (ast.hasException()) return Result(ast.getException()!!.message!!, emptyList())
    return Result("", res)
  }
}
