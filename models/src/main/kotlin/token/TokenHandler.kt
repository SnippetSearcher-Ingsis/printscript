package token

class TokenHandler(val line: List<Token>) {
    private var currentTokenIndex = 0

    fun collectExpressionTokens(): List<Token> {
        val tokens = mutableListOf<Token>()
        while (!isAtEnd() && peek().type != TokenType.SEMICOLON) {
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

    private fun advance(): Token {
        if (!isAtEnd()) currentTokenIndex++
        return previous()
    }

    private fun isAtEnd(): Boolean {
        return currentTokenIndex >= line.size
    }

    private fun peek(): Token {
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
