package com.printscript.lexer

import com.printscript.models.token.TokenType

sealed interface TokenProvider {
  fun getTokenFor(line: String, position: Int): Pair<String, TokenType>?

  operator fun plus(other: TokenProvider): TokenProvider

  fun iterator(): Iterator<Pair<String, TokenType>>

  companion object {
    infix fun builder(tokenMap: Map<String, TokenType>): TokenProvider {
      return TokenProviderImplementation(tokenMap)
    }
  }

  private class TokenProviderImplementation(private val tokens: Map<String, TokenType>) : TokenProvider {
    override fun getTokenFor(line: String, position: Int): Pair<String, TokenType>? {
      for ((pattern, tokenType) in tokens) {
        val regex = Regex(pattern)
        val matchResult = regex.find(line, position)
        if (matchResult != null && matchResult.range.first == position) {
          return matchResult.value to tokenType
        }
      }
      return null
    }

    override fun plus(other: TokenProvider): TokenProvider {
      val map = mutableMapOf<String, TokenType>()
      tokens.forEach { (k, v) -> map[k] = v }
      other.iterator().forEach { (k, v) -> map[k] = v }
      return TokenProviderImplementation(map.toMap())
    }

    override fun iterator() = tokens.map { it.toPair() }.iterator()
  }
}
