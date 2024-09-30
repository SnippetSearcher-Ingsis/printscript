package com.printscript.lexer

import com.printscript.models.token.Token
import com.printscript.models.token.ValuedToken
import java.io.BufferedReader
import java.io.Reader

class Lexer(private val tokenPatterns: TokenProvider) {

  fun lex(reader: Reader): Iterator<List<Token>> {
    return LexerIterator(reader)
  }

  inner class LexerIterator(reader: Reader) : Iterator<List<Token>> {
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

    private fun tokenizeLine(line: String): List<Token> {
      val tokens = mutableListOf<Token>()
      var position = 0

      while (position < line.length) {

        while (position < line.length && line[position].isWhitespace()) {
          position++
          column++
        }

        if (position >= line.length) break

        val matchResult = tokenPatterns.getTokenFor(line, position)

        require(matchResult != null) { "Unexpected character at line $lineNumber, column $column" }

        val (value, tokenType) = matchResult
        val token = ValuedToken(tokenType, value, lineNumber, column)
        tokens.add(token)
        position += value.length
        column += value.length
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
}
