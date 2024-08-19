package printScreen.parser.builder

import node.ASTNode
import node.AssignationNode
import node.Position
import token.Token
import token.TokenHandler
import token.TokenType

class AssignationBuilder(private val line: List<Token>) : Builder {
    override fun build(): ASTNode {
        val handler = TokenHandler(line)
        val identifier = handler.consume(TokenType.IDENTIFIER, "Se esperaba el nombre de la variable.")
        val name = identifier.value
        val position = Position(identifier.line, identifier.column)
        handler.consume(TokenType.EQUAL, "Se esperaba '=' después del nombre de la variable.")
        val expressionTokens = handler.collectExpressionTokens()
        handler.consume(TokenType.SEMICOLON, "Se esperaba ';' después de la declaración.")
        return AssignationNode(name, ExpressionBuilder(expressionTokens).build(), position)
    }
}