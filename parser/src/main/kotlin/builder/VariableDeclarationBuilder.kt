package printScreen.parser.builder

import node.ASTNode
import node.ConstantDeclarationNode
import node.Position
import node.VariableDeclarationNode
import token.Token
import token.TokenHandler
import token.TokenType

class VariableDeclarationBuilder(private val line: List<Token>) : Builder {
  override fun build(): ASTNode {
    val handler = TokenHandler(line)
    val isConst = handler.peek().type == TokenType.CONST
    handler.advance()
    val identifier = handler.consume(TokenType.IDENTIFIER, "Se esperaba el nombre de la variable.")
    val name = identifier.value
    val position = Position(identifier.line, identifier.column)
    handler.consume(TokenType.SYNTAX, "Se esperaba ':' después del nombre de la variable.")
    val type = handler.consume(TokenType.TYPE, "Se esperaba el tipo de la variable.").value
    handler.consume(TokenType.EQUAL, "Se esperaba '=' después del nombre de la variable.")
    val expressionTokens = handler.collectExpressionTokens(false)
    handler.consume(TokenType.SEMICOLON, "Se esperaba ';' después de la declaración.")
    if (isConst) {
      return ConstantDeclarationNode(name, type, ExpressionBuilder(expressionTokens).build(), position)
    }
    return VariableDeclarationNode(name, type, ExpressionBuilder(expressionTokens).build(), position)
  }
}
