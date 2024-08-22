package printScreen.parser

import generator.ASTGenerator
import node.ASTNode
import token.Token

class Parser : IParser {
  override fun parse(tokens: List<Token>?): List<ASTNode> {
    return when {
      tokens == null -> emptyList()
      else -> checkTokens(tokens)
    }
  }

  private fun checkTokens(tokens: List<Token>): List<ASTNode> {
    val astNodes: List<ASTNode> = ASTGenerator().tokensToAST(tokens)
    return astNodes
  }
}
