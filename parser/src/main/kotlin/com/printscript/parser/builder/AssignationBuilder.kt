package com.printscript.parser.builder

import com.printscript.models.node.ASTNode
import com.printscript.models.node.AssignationNode
import com.printscript.models.node.Position
import com.printscript.models.token.Token
import com.printscript.models.token.TokenHandler
import com.printscript.models.token.TokenType

class AssignationBuilder(private val line: List<Token>) : Builder {
  override fun build(): ASTNode {
    val handler = TokenHandler(line)
    val identifier = handler.consume(TokenType.IDENTIFIER, "Se esperaba el nombre de la variable.")
    val name = identifier.value
    val position = Position(identifier.line, identifier.column)
    handler.consume(TokenType.EQUAL, "Se esperaba '=' después del nombre de la variable.")
    val expressionTokens = handler.collectExpressionTokens(false)
    handler.consume(TokenType.SEMICOLON, "Se esperaba ';' después de la declaración.")
    return AssignationNode(name, ExpressionBuilder(expressionTokens).build(), position)
  }
}
