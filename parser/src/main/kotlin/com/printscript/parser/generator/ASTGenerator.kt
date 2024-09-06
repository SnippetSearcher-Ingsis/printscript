package com.printscript.parser.generator

import com.printscript.models.node.ASTNode
import com.printscript.models.token.Token
import com.printscript.models.token.TokenType
import com.printscript.parser.builder.AssignationBuilder
import com.printscript.parser.builder.ConditionalBuilder
import com.printscript.parser.builder.PrintStatementBuilder
import com.printscript.parser.builder.VariableDeclarationBuilder

class ASTGenerator {
  private val dataToToken = mapOf(
    TokenType.IDENTIFIER to { tokens: List<Token> -> AssignationBuilder(tokens).build() },
    TokenType.LET to { tokens: List<Token> -> VariableDeclarationBuilder(tokens).build() },
    TokenType.CONST to { tokens: List<Token> -> VariableDeclarationBuilder(tokens).build() },
    TokenType.IF to { tokens: List<Token> -> ConditionalBuilder(tokens).build() },
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
