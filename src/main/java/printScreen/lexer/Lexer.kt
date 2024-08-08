package printScreen.lexer

import printScreen.models.token.NonValuedToken
import printScreen.models.token.Token
import printScreen.models.token.TokenType
import printScreen.models.token.ValuedToken

class Lexer {
    fun lex(input: String): List<Token>? {
        val result: MutableList<Token> = mutableListOf()
        var column = 1
        var line = 1
        var i = 0
        while (i < input.length) {
            if (isWhitespace(input[i])) {
                if (input[i] == '\n') {
                    line++
                    column = 1
                } else {
                    column++
                }
            } else if (isOperator(input[i])) {
                result.add(ValuedToken(TokenType.OPERATOR, input[i].toString(), line, column))
                column++
            } else if (isSyntax(input[i])) {
                result.add(ValuedToken(TokenType.SYNTAX, input[i].toString(), line, column))
                column++
            } else if (isLiteral(input[i])) {
                if (input[i] == '"') {
                    val end = checkString(input, i)
                    result.add(ValuedToken(TokenType.LITERAL, input.substring(i, end), line, column))
                    column += end - i + 1
                    i = end
                } else {
                    val end = checkNumber(input, i)
                    result.add(ValuedToken(TokenType.LITERAL, input.substring(i, end + 1), line, column))
                    column += end - i + 1
                    i = end
                }
            } else {
                val end = checkForToken(input.substring(i), line, column)
                if (end != null) {
                    result.add(ValuedToken(TokenType.KEYWORD, input.substring(i, i + end), line, column))
                    column += end
                    i += end
                } else {
                    throw Exception("Invalid character at line $line, column $column")
                }
            }
            i++
        }
    }

    fun isWhitespace(char: Char): Boolean {
        return char == ' ' || char == '\t' || char == '\n'
    }

    fun isOperator(char: Char): Boolean {
        return char == '+' || char == '-' || char == '*' || char == '/' || char == '%' || char == '=' || char == '!' || char == '>' || char == '<' || char == '&' || char == '|'
    }

    fun isSyntax(char: Char): Boolean {
        return char == '(' || char == ')' || char == '{' || char == '}' || char == '[' || char == ']' || char == ',' || char == ';' || char == '.'
    }

    fun isLiteral(char: Char): Boolean {
        return char == '"' || char == '\'' || char.isDigit()
    }

    fun checkString(input: String, index: Int): Int { // index is the index of the first character of the string
        var i = index + 1
        while (i < input.length) {
            if (input[i] == '"') {
                return i + 1
            }
            i++
        }
        throw Exception("String not closed")
    }

    fun checkNumber(input: String, index: Int): Int {
        var i = index + 1
        while (i < input.length) {
            if (!input[i].isDigit()) {
                return i
            }
            i++
        }
        return i
    }

    fun checkKeyWord(input: String, index: Int): Int? {
        val keywords = listOf("if", "else", "while", "for", "fun", "return", "break", "continue", "let", "true", "false")
        var i = index
        var word = input[i].toString()
        while (i < input.length) {
            if (word in keywords && ) {
                return word.length
            }
            if (input[i].isLetterOrDigit() || input[i] == '_') {
                word += input[i]
            } else {
                break
            }
            i++
        }
        return null
    }
}