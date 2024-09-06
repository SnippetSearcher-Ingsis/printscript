package com.printscript.parser.generator

import com.printscript.parser.builder.AssignationBuilder
import com.printscript.parser.builder.PrintStatementBuilder
import com.printscript.parser.builder.VariableDeclarationBuilder

class ASTGenerator {
  private val dataToToken = mapOf(
    com.printscript.models.token.TokenType.IDENTIFIER to { tokens: List<com.printscript.models.token.Token> -> AssignationBuilder(tokens).build() },
    com.printscript.models.token.TokenType.LET to { tokens: List<com.printscript.models.token.Token> -> VariableDeclarationBuilder(tokens).build() },
    com.printscript.models.token.TokenType.PRINTLN to { tokens: List<com.printscript.models.token.Token> -> PrintStatementBuilder(tokens).build() }
  )

  fun tokensToAST(tokens: List<com.printscript.models.token.Token>): com.printscript.models.node.ASTNode {
    return createAST(tokens)
  }

  private fun createAST(line: List<com.printscript.models.token.Token>): com.printscript.models.node.ASTNode {
    val firstToken: com.printscript.models.token.Token = line[0]
    return dataToToken[firstToken.type]?.invoke(line) ?: error("Unknown token ${firstToken.type}")
  }
}
