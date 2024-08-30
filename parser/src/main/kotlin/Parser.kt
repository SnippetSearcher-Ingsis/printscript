package printScreen.parser

import generator.ASTGenerator
import node.ASTNode
import token.Token

class Parser(private val tokens: Iterator<List<Token>>) : Iterator<ASTNode> {
  private val astGenerator = ASTGenerator()

  override fun hasNext(): Boolean {
    return tokens.hasNext()
  }

  override fun next(): ASTNode {
    if (!tokens.hasNext()) throw NoSuchElementException("No more AST nodes to parse.")
    return astGenerator.tokensToAST(tokens.next())
  }
}
