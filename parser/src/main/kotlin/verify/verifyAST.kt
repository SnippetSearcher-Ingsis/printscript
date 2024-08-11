package printScreen.parser

import token.Token
import token.TokenType


class verifyAST(private val tokens: List<List<Token>>) {
    private var current = 0

    fun validate() {
        while (!isAtEnd()) {
            validateDeclaration()
        }
    }

    private fun validateDeclaration() {
        if (match(TokenType.KEYWORD) && previous().value == "let") {
            consume(TokenType.IDENTIFIER, "Se esperaba el nombre de la variable.")
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
            else -> throw IllegalArgumentException("Expresión inesperada en la posición $current.")
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
        if (!isAtEnd()) current++
        return previous()
    }

    private fun isAtEnd(): Boolean {
        return current >= tokens.size
    }

    private fun peek(): Token = tokens[current]
    private fun previous(): Token = tokens[current - 1]

    private fun consume(type: TokenType, message: String): Token {
        if (check(type)) return advance()
        throw IllegalArgumentException(message)
    }
}
