package printScreen.lexer

import token.Token
import token.TokenType
import token.ValuedToken

class Lexer(private val sourceCode: String) {
    private var position: Int = 0
    private var line: Int = 1
    private var column: Int = 1

    private val tokenPatterns = listOf(
        "\\blet\\b" to TokenType.LET,
        "\\bprintln\\b" to TokenType.PRINTLN,
        "\\bstring\\b" to TokenType.TYPE,
        "\\bnumber\\b" to TokenType.TYPE,
        "[a-zA-Z_][a-zA-Z_0-9]*" to TokenType.IDENTIFIER,
        "[=]" to TokenType.EQUAL,
        "[0-9]+(\\.[0-9]+)?" to TokenType.LITERAL,
        "\"[^\"]*\"|'[^']*'" to TokenType.LITERAL,
        "[+\\-*/]" to TokenType.OPERATOR,
        "[:(){}]" to TokenType.SYNTAX,
        "[;]" to TokenType.SEMICOLON
    )

    fun tokenize(): List<Token> {
        val tokens = mutableListOf<Token>()
        while (position < sourceCode.length) {
            val (token, newPos) = getNextToken()
            if (token != null) tokens.add(token)
            position = newPos
        }
        return tokens
    }

    private fun getNextToken(): Pair<Token?, Int> {

        while (position < sourceCode.length && sourceCode[position].isWhitespace()) {
            if (sourceCode[position] == '\n') {
                line++
                column = 0
            }
            column++
            position++
        }

        if (position >= sourceCode.length) return null to position

        for ((pattern, tokenType) in tokenPatterns) {
            val regex = Regex(pattern)
            val matchResult = regex.find(sourceCode, position)
            if (matchResult != null && matchResult.range.first == position) {
                val value = matchResult.value
                val token = ValuedToken(tokenType, value, line, column)
                column += value.length
                return token to position + value.length
            }
        }
        throw IllegalArgumentException("Unexpected character at position $position")
    }
}