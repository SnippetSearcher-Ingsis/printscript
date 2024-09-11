package com.printscript.parser.builder

import com.printscript.models.node.ASTNode
import com.printscript.models.node.ConstantDeclarationNode
import com.printscript.models.node.ConstantNode
import com.printscript.models.node.LiteralNode
import com.printscript.models.node.Position
import com.printscript.models.node.VariableDeclarationNode
import com.printscript.models.node.VariableNode
import com.printscript.models.token.Token
import com.printscript.models.token.TokenHandler
import com.printscript.models.token.TokenType

class VariableDeclarationBuilder(private val line: List<Token>) : Builder {

  override fun build(): ASTNode {
    val handler = TokenHandler(line)
    val isConst = handler.advance().type == TokenType.CONST
    val identifier = handler.consume(TokenType.IDENTIFIER, "Se esperaba el nombre de la variable.")
    val position = Position(identifier.line, identifier.column)

    handler.consume(TokenType.SYNTAX, "Se esperaba ':' después del nombre de la variable.")
    val type = handler.consume(TokenType.TYPE, "Se esperaba el tipo de la variable.").value

    val node = if (handler.peek().type == TokenType.SEMICOLON) {
      if (isConst) ConstantNode(identifier.value, type, LiteralNode("empty"), position)
      else VariableNode(identifier.value, type, LiteralNode("empty"), position)
    } else {
      handler.consume(TokenType.EQUAL, "Se esperaba '=' después del nombre de la variable.")
      val tokens = handler.collectExpressionTokens(false)
      handler.consume(TokenType.SEMICOLON, "Se esperaba ';' después de la declaración.")
      val resolvedExpression: ASTNode = resolveExpression(tokens[0], tokens)
      if (isConst) ConstantDeclarationNode(identifier.value, type, resolvedExpression, position)
      else VariableDeclarationNode(identifier.value, type, resolvedExpression, position)
    }

    return node
  }

  private fun resolveExpression(firstToken: Token, tokens: List<Token>): ASTNode {
    return when (firstToken.type) {
      TokenType.READ_INPUT -> {
        ReadInputBuilder(tokens).build()
      }
      TokenType.READ_ENV -> {
        ReadEnvBuilder(tokens).build()
      }
      else -> {
        ExpressionBuilder(tokens).build()
      }
    }
  }
}
