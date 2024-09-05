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
    val handler = TokenHandler(line)
    handler.advance()

    val condition = parseCondition(handler)
    val ifBranch = parseBranch(handler)

    val elseBranch = if (handler.isAtEnd() || handler.peek().type != TokenType.ELSE) {
      emptyList()
    } else {
      parseElseBranch(handler)
    }

    return IfElseNode(ifBranch, elseBranch, LiteralNode(condition))
  }

  private fun parseCondition(handler: TokenHandler): String {
    handler.consume(TokenType.SYNTAX, "Se esperaba '('")
    val condition = when (handler.peek().type) {
      TokenType.IDENTIFIER -> handler.consume(TokenType.IDENTIFIER, "Se esperaba un identificador").value
      TokenType.LITERAL -> handler.consume(TokenType.LITERAL, "Se esperaba un boolean").value
      else -> throw Exception("Se esperaba un identificador o un booleano.")
    }
    handler.consume(TokenType.SYNTAX, "Se esperaba ')'")
    return condition
  }

  private fun parseBranch(handler: TokenHandler): List<ASTNode> {
    val branch = mutableListOf<ASTNode>()
    handler.consume(TokenType.OPEN_BRACKET, "Se esperaba '{'")
    while (handler.peek().type != TokenType.CLOSE_BRACKET) {
      branch.add(ASTGenerator().tokensToAST(handler.collectExpressionTokens(true)))
    }
    handler.consume(TokenType.CLOSE_BRACKET, "Se esperaba '}'")
    return branch
  }

  private fun parseElseBranch(handler: TokenHandler): List<ASTNode> {
    handler.advance()
    return parseBranch(handler)
  }
}
