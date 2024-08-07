package lexer

import token.Token

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

    fun checkForToken() {}
}