package printScreen.parser

import catchable.ICatchable
import node.ASTNode
import node.ErrorNode
import token.Token

class CatchableParser : IParser {

  override fun parse(tokens: Iterator<List<Token>>): CatchableParserIterator {
    return CatchableParserIterator(tokens)
  }

  inner class CatchableParserIterator(tokens: Iterator<List<Token>>) : Iterator<ASTNode>, ICatchable {

    private val parser = Parser().parse(tokens)

    private var exception: Exception? = null

    override fun hasNext(): Boolean {
      return exception == null && parser.hasNext()
    }

    override fun next(): ASTNode {
      return try {
        parser.next()
      } catch (e: Exception) {
        exception = e
        ErrorNode(e.message.toString())
      }
    }

    override fun hasException(): Boolean = exception != null

    override fun getException(): Exception? = exception
  }
}
