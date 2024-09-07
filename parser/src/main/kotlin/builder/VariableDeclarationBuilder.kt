package printScreen.parser.builder

import node.ASTNode
import node.ConstantDeclarationNode
import node.ConstantNode
import node.LiteralNode
import node.Position
import node.VariableDeclarationNode
import node.VariableNode
import token.Token
import token.TokenHandler
import token.TokenType

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
        val expression = generateExpression(handler) // handler de marquiños para ver si tiene readEnv()
      val tokens = handler.collectExpressionTokens(false)
      handler.consume(TokenType.SEMICOLON, "Se esperaba ';' después de la declaración.")
      val resolvedExpression : ASTNode =  resolveExpression(tokens[0], tokens)
      if (isConst) ConstantDeclarationNode(identifier.value, type, resolvedExpression, position)
      else VariableDeclarationNode(identifier.value, type, resolvedExpression, position)
    }

    return node
  }

  private fun resolveExpression(firstToken : Token, tokens: List<Token>): ASTNode {
    return when {
      firstToken.type == TokenType.READ_INPUT -> {
        ReadInputBuilder(tokens).build()
      }
        firstToken.type == TokenType.READ_ENV -> {
            ReadEnvBuilder(handler.collectExpressionTokens(false)).build()
        }
      else -> {
        ExpressionBuilder(tokens).build()
      }
    }
  }

  private fun generateExpression(handler: TokenHandler): ASTNode  {
    val expression: ASTNode
    if (handler.peek().type == TokenType.READ_ENV) {
      expression = ReadEnvBuilder(handler.collectExpressionTokens(false)).build()
    } else {
      expression = ExpressionBuilder(handler.collectExpressionTokens(false)).build()
    }
    return expression
  }
}
