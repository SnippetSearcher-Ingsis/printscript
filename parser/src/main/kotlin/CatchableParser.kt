package printScreen.parser

import catchable.ICatchable
import node.ASTNode
import token.Token

class CatchableParser : IParser, ICatchable {
  private val parser = Parser()

  private var exception: Exception? = null

  override fun parse(tokens: List<Token>?): List<ASTNode> {
    return try {
      parser.parse(tokens)
    } catch (e: Exception) {
      exception = e
      emptyList()
    }
  }

  override fun hasException(): Boolean = exception != null

  override fun getException(): Exception? = exception
}
