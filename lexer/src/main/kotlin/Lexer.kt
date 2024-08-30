package printScreen.lexer

import token.Token
import token.TokenType
import token.ValuedToken
import java.io.BufferedReader
import java.io.Reader

class Lexer(reader: Reader) : Iterator<List<Token>> {
  private val bufferedReader: BufferedReader = reader.buffered()
  private var currentLine: String? = null
  private var lineNumber: Int = 0
  private var column: Int = 0

  private val tokenPatterns = listOf(
    "\\blet\\b" to TokenType.LET,
    "\\bprintln\\b" to TokenType.PRINTLN,
    "\\bstring\\b" to TokenType.TYPE,
    "\\bnumber\\b" to TokenType.TYPE,
    "[a-zA-Z_][a-zA-Z_0-9-]*" to TokenType.IDENTIFIER,
    "[=]" to TokenType.EQUAL,
    "[0-9]+(\\.[0-9]+)?" to TokenType.LITERAL,
    "\"[^\"]*\"|'[^']*'" to TokenType.LITERAL,
    "[+\\-*/]" to TokenType.OPERATOR,
    "[:(){}]" to TokenType.SYNTAX,
    "[;]" to TokenType.SEMICOLON
  )

  init {
    advanceLine()
  }

  private fun advanceLine() {
    currentLine = bufferedReader.readLine()
    lineNumber++
    column = 0
  }

  private fun tokenizeLine(line: String): List<Token> {
    val tokens = mutableListOf<Token>()
    var position = 0

    while (position < line.length) {

      while (position < line.length && line[position].isWhitespace()) {
        position++
        column++
      }

      if (position >= line.length) break

      var matched = false
      for ((pattern, tokenType) in tokenPatterns) {
        val regex = Regex(pattern)
        val matchResult = regex.find(line, position)
        if (matchResult != null && matchResult.range.first == position) {
          val value = matchResult.value
          val token = ValuedToken(tokenType, value, lineNumber, column)
          tokens.add(token)
          position += value.length
          column += value.length
          matched = true
          break
        }
      }

      if (!matched) {
        throw IllegalArgumentException("Unexpected character at line $lineNumber, column $column")
      }
    }

    return tokens
  }

  private fun skipEmptyLines() {
    while (currentLine != null && currentLine!!.trim().isEmpty()) {
      advanceLine()
    }
  }

  override fun hasNext(): Boolean {
    skipEmptyLines()
    return currentLine != null
  }

  override fun next(): List<Token> {
    if (!hasNext()) throw NoSuchElementException("No more lines to read")

    val lineTokens = tokenizeLine(currentLine!!)
    advanceLine()
    return lineTokens
  }
}
