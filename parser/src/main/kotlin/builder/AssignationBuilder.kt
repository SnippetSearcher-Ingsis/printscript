package printScreen.parser.builder

import node.ASTNode
import node.AssignationNode
import token.Token
import token.TokenHandler
import token.TokenType

class AssignationBuilder(private val line: List<Token>) : Builder {
    override fun build(): ASTNode {
        val handler = TokenHandler(line)
        val name = handler.consume(TokenType.IDENTIFIER, "Se esperaba el nombre de la variable.").value
        handler.consume(TokenType.EQUAL, "Se esperaba '=' después del nombre de la variable.")
        val expressionTokens = handler.collectExpressionTokens()
        handler.consume(TokenType.SEMICOLON, "Se esperaba ';' después de la declaración.")
        return AssignationNode(name, ExpressionBuilder(expressionTokens).build())
    }
}
