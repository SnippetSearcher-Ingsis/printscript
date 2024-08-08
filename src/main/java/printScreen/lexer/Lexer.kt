package printScreen.lexer

import printScreen.models.token.NonValuedToken
import printScreen.models.token.Token
import printScreen.models.token.TokenType

class Lexer {
    fun lex(input: String): List<Token>? {
        val result: MutableList<Token> = mutableListOf()
        var column = 1
        var line = 1
        return null
    }

    fun isWhitespace(char: Char): Boolean {
        return char == ' ' || char == '\t' || char == '\n'
    }

    fun checkForToken(input: String, line: Int, column: Int): TokenType? {
        when (input) {
            "let" -> return TokenType.LET_KEYWORD
            ";" -> return TokenType.SEMICOLON
        }
        if (input[0] == '"' && input[input.length - 1] == '"') {
            return TokenType.STRING
        }
        return null
    }
}