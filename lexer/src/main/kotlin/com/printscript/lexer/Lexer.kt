package com.printscript.lexer

import java.io.BufferedReader
import java.io.Reader

class Lexer {

  private val tokenPatterns: List<Pair<String, com.printscript.models.token.TokenType>> = listOf(
    "\\blet\\b" to com.printscript.models.token.TokenType.LET,
    "\\bprintln\\b" to com.printscript.models.token.TokenType.PRINTLN,
    "\\bstring\\b" to com.printscript.models.token.TokenType.TYPE,
    "\\bnumber\\b" to com.printscript.models.token.TokenType.TYPE,
    "\\bboolean\\b" to com.printscript.models.token.TokenType.TYPE,
    "\\btrue\\b" to com.printscript.models.token.TokenType.LITERAL,
    "\\bfalse\\b" to com.printscript.models.token.TokenType.LITERAL,
    "[a-zA-Z_][a-zA-Z_0-9-]*" to com.printscript.models.token.TokenType.IDENTIFIER,
    "[=]" to com.printscript.models.token.TokenType.EQUAL,
    "[0-9]+(\\.[0-9]+)?" to com.printscript.models.token.TokenType.LITERAL,
    "\"[^\"]*\"|'[^']*'" to com.printscript.models.token.TokenType.LITERAL,
    "[+\\-*/]" to com.printscript.models.token.TokenType.OPERATOR,
    "[{]" to com.printscript.models.token.TokenType.OPEN_BRACKET,
    "[}]" to com.printscript.models.token.TokenType.CLOSE_BRACKET,
    "[:()]" to com.printscript.models.token.TokenType.SYNTAX,
    "[;]" to com.printscript.models.token.TokenType.SEMICOLON
  )

  fun lex(reader: Reader): Iterator<List<com.printscript.models.token.Token>> {
    return LexerIterator(reader)
  }

  inner class LexerIterator(reader: Reader) : Iterator<List<com.printscript.models.token.Token>> {
    private val bufferedReader: BufferedReader = reader.buffered()
    private var currentLine: String? = null
    private var lineNumber: Int = 0
    private var column: Int = 1

    init {
      advanceLine()
    }

    private fun advanceLine() {
      currentLine = bufferedReader.readLine()
      lineNumber++
      column = 1
    }

    private fun tokenizeLine(line: String): List<com.printscript.models.token.Token> {
      val tokens = mutableListOf<com.printscript.models.token.Token>()
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
            val token = com.printscript.models.token.ValuedToken(tokenType, value, lineNumber, column)
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

    override fun next(): List<com.printscript.models.token.Token> {
      if (!hasNext()) throw NoSuchElementException("No more lines to read")

      val lineTokens = tokenizeLine(currentLine!!)
      advanceLine()
      return lineTokens
    }
  }
}
