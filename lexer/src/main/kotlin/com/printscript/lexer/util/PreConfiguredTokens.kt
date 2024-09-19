package com.printscript.lexer.util

import com.printscript.lexer.TokenProvider
import com.printscript.models.token.TokenType

object PreConfiguredTokens {
  val TOKENS_1_0 = TokenProvider builder (
    mapOf(
      "//.*$" to TokenType.COMMENT,
      "\\blet\\b" to TokenType.LET,
      "\\bprintln\\b" to TokenType.PRINTLN,
      "\\bstring\\b" to TokenType.TYPE,
      "\\bnumber\\b" to TokenType.TYPE,
      "[a-zA-Z_][a-zA-Z_0-9-]*" to TokenType.IDENTIFIER,
      "[=]" to TokenType.EQUAL,
      "[0-9]+(\\.[0-9]+)?" to TokenType.LITERAL,
      "\"[^\"]*\"|'[^']*'" to TokenType.LITERAL,
      "[+\\-*/]" to TokenType.OPERATOR,
      "[{]" to TokenType.OPEN_BRACKET,
      "[}]" to TokenType.CLOSE_BRACKET,
      "[:()]" to TokenType.SYNTAX,
      "[;]" to TokenType.SEMICOLON
    )
    )

  val TOKENS_1_1 = TokenProvider.builder(
    mapOf(
      "\\bconst\\b" to TokenType.CONST,
      "\\bif\\b" to TokenType.IF,
      "\\belse\\b" to TokenType.ELSE,
      "\\breadInput\\b" to TokenType.READ_INPUT,
      "\\breadEnv\\b" to TokenType.READ_ENV,
      "\\bboolean\\b" to TokenType.TYPE,
      "\\btrue\\b" to TokenType.LITERAL,
      "\\bfalse\\b" to TokenType.LITERAL,
    )
  ) + TOKENS_1_0
}
