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

  fun tokensToAST(tokens: List<Token>): ASTNode {
    return createAST(tokens)
  }

  private fun createAST(line: List<Token>): ASTNode {
    val firstToken: Token = line[0]
    return dataToToken[firstToken.type]?.invoke(line) ?: error("Unknown token ${firstToken.type}")
  }
}
