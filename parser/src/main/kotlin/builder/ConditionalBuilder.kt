package printScreen.parser.builder

import generator.ASTGenerator
import node.ASTNode
import node.IfElseNode
import node.LiteralNode
import token.Token
import token.TokenHandler
import token.TokenType

class ConditionalBuilder(private val line: List<Token>) : Builder {
  override fun build(): ASTNode {
    val ifBranch = mutableListOf<ASTNode>()
    val elseBranch = mutableListOf<ASTNode>()
    val handler = TokenHandler(line)
    handler.advance()
    handler.consume(TokenType.SYNTAX, "Se esperaba '('")
    val condition = LiteralNode(
      handler.consume(
        TokenType.IDENTIFIER, "Se esperaba el nombre de la variable boolean."
      ).value
    )
    handler.consume(TokenType.SYNTAX, "Se esperaba ')'")
    handler.consume(TokenType.OPEN_BRACKET, "Se esperaba '{'")
    while (handler.peek().type != TokenType.CLOSE_BRACKET) {
      ifBranch.add(ASTGenerator().tokensToAST(handler.collectExpressionTokens(true)))
    }
    handler.consume(TokenType.CLOSE_BRACKET, "Se esperaba '}'")
    if (!handler.isAtEnd() && handler.peek().type == TokenType.ELSE) {
      handler.advance()
      handler.consume(TokenType.OPEN_BRACKET, "Se esperaba '{'")
      while (handler.peek().type != TokenType.CLOSE_BRACKET) {
        elseBranch.add(ASTGenerator().tokensToAST(handler.collectExpressionTokens(true)))
      }
      handler.consume(TokenType.CLOSE_BRACKET, "Se esperaba '}'")
    }
    return IfElseNode(ifBranch, elseBranch, condition)
  }
}
