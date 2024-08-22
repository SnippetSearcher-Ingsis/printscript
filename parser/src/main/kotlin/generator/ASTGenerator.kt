package generator

import node.ASTNode
import printScreen.parser.builder.AssignationBuilder
import printScreen.parser.builder.PrintStatementBuilder
import printScreen.parser.builder.VariableDeclarationBuilder
import token.Token
import token.TokenType

class ASTGenerator {

  private val dataToToken = mapOf(
    TokenType.IDENTIFIER to { tokens: List<Token> -> AssignationBuilder(tokens).build() },
    TokenType.LET to { tokens: List<Token> -> VariableDeclarationBuilder(tokens).build() },
    TokenType.PRINTLN to { tokens: List<Token> -> PrintStatementBuilder(tokens).build() }
  )

  fun tokensToAST(tokens: List<Token>): List<ASTNode> {
    val split: List<List<Token>> = splitEachLine(tokens)
    return if (split.isEmpty()) emptyList() else split.map { token -> createAST(token) }
  }

  private fun splitEachLine(tokens: List<Token>): List<List<Token>> {
    var i = 0
    val result = ArrayList<List<Token>>()
    var line = mutableListOf<Token>()
    while (i != tokens.size) {
      val token = tokens[i]
      when {
        isEndOfLine(token) -> {
          line.add(token)
          result.add(line.map { token -> token })
          line = mutableListOf()
        }

        else -> {
          line.add(tokens[i])
        }
      }
      i++
    }
    if (line.isNotEmpty()) result.add(line)
    return result
  }

  private fun isEndOfLine(token: Token): Boolean {
    return token.type == TokenType.SEMICOLON
  }

  private fun createAST(line: List<Token>): ASTNode {
    val firstToken: Token = line[0]
    return dataToToken[firstToken.type]?.invoke(line) ?: error("Unknown token ${firstToken.type}")
  }
}
