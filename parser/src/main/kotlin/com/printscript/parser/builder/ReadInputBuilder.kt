package com.printscript.parser.builder

import com.printscript.models.node.ASTNode
import com.printscript.models.node.Position
import com.printscript.models.node.ReadInputNode
import com.printscript.models.token.Token
import com.printscript.models.token.TokenHandler
import com.printscript.models.token.TokenType

class ReadInputBuilder(private val line: List<Token>) : Builder {
  override fun build(): ASTNode {
    val handler = TokenHandler(line)
    val readInput = handler.consume(TokenType.READ_INPUT, "Se esperaba 'readInput' al principio de la declaración.")
    val position = Position(readInput.line, readInput.column)
    handler.consume(TokenType.SYNTAX, "Se esperaba '(' después de 'println'.")
    val expressionTokens = handler.collectExpressionTokensInParenthesis()
    handler.consume(TokenType.SYNTAX, "Se esperaba ')' después de la expresión.")
    return ReadInputNode(ExpressionBuilder(expressionTokens).build(), position)
  }
}
