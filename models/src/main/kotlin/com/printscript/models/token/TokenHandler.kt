package com.printscript.models.token

data class TokenHandler(val line: List<Token>) {
  private var currentTokenIndex = 0

  fun collectExpressionTokens(with: Boolean): List<Token> {
    val tokens = mutableListOf<Token>()
    while (!isAtEnd() && peek().type != TokenType.SEMICOLON) {
      tokens.add(advance())
    }
    if (with) {
      tokens.add(advance())
    }
    return tokens
  }

  fun collectExpressionTokensInParenthesis(): List<Token> {
    val tokens = mutableListOf<Token>()
    while (!isAtEnd() && peek().type != TokenType.SYNTAX && peek().value != ")") {
      tokens.add(advance())
    }
    return tokens
  }

  fun advance(): Token {
    if (!isAtEnd()) currentTokenIndex++
    return previous()
  }

  fun isAtEnd(): Boolean {
    return currentTokenIndex >= line.size
  }

  fun peek(): Token {
    val m = "Expected token but reached end of line. At line ${line.last().line} column ${line.last().column}"
    check(currentTokenIndex < line.size) { m }
    return line[currentTokenIndex]
  }

  fun consume(type: TokenType, message: String): Token {
    if (check(type)) return advance()
    throw IllegalArgumentException(message + " At line ${peek().line} column ${peek().column}")
  }

  private fun check(type: TokenType): Boolean {
    return !isAtEnd() && peek().type == type
  }

  private fun previous(): Token {
    check(currentTokenIndex >= 0) { "No previous token." }
    return line[currentTokenIndex - 1]
  }
}
