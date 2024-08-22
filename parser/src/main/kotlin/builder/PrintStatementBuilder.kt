package printScreen.parser.builder

import node.ASTNode
import node.Position
import node.PrintStatementNode
import token.Token
import token.TokenHandler
import token.TokenType

class PrintStatementBuilder(private val line: List<Token>) : Builder {
  override fun build(): ASTNode {
    val handler = TokenHandler(line)
    val print = handler.consume(TokenType.PRINTLN, "Se esperaba 'println' al principio de la declaración.")
    val position = Position(print.line, print.column)
    handler.consume(TokenType.SYNTAX, "Se esperaba '(' después de 'println'.")
    val expressionTokens = handler.collectExpressionTokensInParenthesis()
    handler.consume(TokenType.SYNTAX, "Se esperaba ')' después de la expresión.")
    handler.consume(TokenType.SEMICOLON, "Se esperaba ';' después de la declaración.")
    return PrintStatementNode(ExpressionBuilder(expressionTokens).build(), position)
  }
}
