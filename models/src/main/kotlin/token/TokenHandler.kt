package token

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

  // temporal hasta encontrar una solucion mejor
  fun collectExpressionTokensInParenthesis(): List<Token> {
    val tokens = mutableListOf<Token>()
    while (!isAtEnd() && peek().type != TokenType.SYNTAX && peek().value != ")") {
      tokens.add(advance())
    }
    return tokens
  }

  private fun check(type: TokenType): Boolean {
    return !isAtEnd() && peek().type == type
  }

  fun advance(): Token {
    if (!isAtEnd()) currentTokenIndex++
    return previous()
  }

  fun isAtEnd(): Boolean {
    return currentTokenIndex >= line.size
  }

  fun peek(): Token {
    return if (currentTokenIndex < line.size) {
      line[currentTokenIndex]
    } else {
      throw IllegalStateException("No more tokens to peek.")
    }
  }

  private fun previous(): Token {
    if (currentTokenIndex > 0) {
      return line[currentTokenIndex - 1]
    } else {
      throw IllegalStateException("No previous token.")
    }
  }

  fun consume(type: TokenType, message: String): Token {
    if (check(type)) return advance()
    throw IllegalArgumentException(message)
  }
}
