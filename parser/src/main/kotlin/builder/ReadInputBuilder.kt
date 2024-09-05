package printScreen.parser.builder

import node.ASTNode
import node.Position
import node.ReadInputNode
import token.Token
import token.TokenHandler
import token.TokenType

class ReadInputBuilder (private val line: List<Token>, private val position: Position)  : Builder {
    override fun build(): ASTNode {
        val handler = TokenHandler(line)
        handler.consume(TokenType.READ_INPUT, "Se esperaba 'readInput'.")
        handler.consume(TokenType.OPEN_BRACKET, "Se esperaba '('.")
        val expressionTokens = handler.collectExpressionTokensInParenthesis()
        handler.consume(TokenType.SYNTAX, "Se esperaba ')'.")
        return ReadInputNode(ExpressionBuilder(expressionTokens).build(), position)
    }
}
