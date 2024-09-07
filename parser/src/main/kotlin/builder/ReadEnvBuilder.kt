package printScreen.parser.builder

import node.ASTNode
import node.Position
import node.ReadEnvNode
import token.Token
import token.TokenHandler
import token.TokenType

class ReadEnvBuilder(private val line: List<Token>) : Builder {
    override fun build(): ASTNode {
        val handler = TokenHandler(line)
        val print = handler.consume(TokenType.READ_ENV, "Se esperaba 'println' al principio de la declaración.")
        val position = Position(print.line, print.column)
        handler.consume(TokenType.SYNTAX, "Se esperaba '(' después de 'println'.")
        val expressionTokens = handler.collectExpressionTokensInParenthesis()
        handler.consume(TokenType.SYNTAX, "Se esperaba ')' después de la expresión.")
        return ReadEnvNode(ExpressionBuilder(expressionTokens).build(), position)
    }
}