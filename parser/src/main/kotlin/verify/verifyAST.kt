package printScreen.parser.verify

import token.Token
import token.TokenType

class VerifyAST(private val tokens: List<List<Token>>) {
    private var currentListIndex = 0
    private var currentTokenIndex = 0

    fun validate() {
        while (!isAtEnd()) {
            validateDeclaration()
        }
    }

    private fun validateDeclaration() {
        if (match(TokenType.KEYWORD) && previous().value == "let") {
            consume(TokenType.IDENTIFIER, "Se esperaba el nombre de la variable.")
            consume(TokenType.SYNTAX, "Se esperaba ':' después del nombre de la variable.")
            consume(TokenType.KEYWORD, "Se esperaba el tipo de la variable.")
            consume(TokenType.SYNTAX, "Se esperaba '=' después del nombre de la variable.")
            validateExpression()
            consume(TokenType.SYNTAX, "Se esperaba ';' después de la declaración.")
        } else {
            validateStatement()
        }
    }

    private fun validateStatement() {
        if (match(TokenType.KEYWORD) && previous().value == "println") {
            consume(TokenType.SYNTAX, "Se esperaba '(' después de 'println'.")
            validateExpression()
            consume(TokenType.SYNTAX, "Se esperaba ')' después del valor.")
            consume(TokenType.SYNTAX, "Se esperaba ';' después del valor.")
        } else {
            validateExpression()
            consume(TokenType.SYNTAX, "Se esperaba ';' después de la expresión.")
        }
    }

    private fun validateExpression() {
        validatePrimary()
        while (match(TokenType.OPERATOR)) {
            validatePrimary()
        }
    }

    private fun validatePrimary() {
        when {
            match(TokenType.LITERAL) -> return
            match(TokenType.IDENTIFIER) -> return
            else -> throw IllegalArgumentException("Expresión inesperada en la posición $currentListIndex:$currentTokenIndex.")
        }
    }

    private fun match(vararg types: TokenType): Boolean {
        for (type in types) {
            if (check(type)) {
                advance()
                return true
            }
        }
        return false
    }

    private fun check(type: TokenType): Boolean {
        return if (isAtEnd()) false else peek().type == type
    }

    private fun advance(): Token {
        if (!isAtEnd()) currentTokenIndex++
        return previous()
    }

    private fun isAtEnd(): Boolean {
        if (currentListIndex >= tokens.size) return true
        if (currentTokenIndex >= tokens[currentListIndex].size) {
            currentListIndex++
            currentTokenIndex = 0
            return isAtEnd()
        }
        return false
    }

    private fun peek(): Token {
        return if (currentListIndex < tokens.size && currentTokenIndex < tokens[currentListIndex].size) {
            tokens[currentListIndex][currentTokenIndex]
        } else {
            throw IllegalStateException("No more tokens to peek.")
        }
    }

    private fun previous(): Token {
        if (currentTokenIndex > 0) {
            return tokens[currentListIndex][currentTokenIndex - 1]
        } else {
            throw IllegalStateException("No previous token.")
        }
    }

    private fun consume(type: TokenType, message: String): Token {
        if (check(type)) return advance()
        throw IllegalArgumentException(message)
    }
}
