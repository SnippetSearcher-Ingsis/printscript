package com.printscript.parser

class CatchableParser : IParser {

  override fun parse(tokens: Iterator<List<com.printscript.models.token.Token>>): CatchableParserIterator {
    return CatchableParserIterator(tokens)
  }

  inner class CatchableParserIterator(tokens: Iterator<List<com.printscript.models.token.Token>>) :
    Iterator<com.printscript.models.node.ASTNode>,
    com.printscript.models.catchable.ICatchable {

    private val parser = Parser().parse(tokens)

    private var exception: Exception? = null

    override fun hasNext(): Boolean {
      return exception == null && parser.hasNext()
    }

    override fun next(): com.printscript.models.node.ASTNode {
      return try {
        parser.next()
      } catch (e: Exception) {
        exception = e
        com.printscript.models.node.ErrorNode(e.message.toString())
      }
    }

    override fun hasException(): Boolean = exception != null

    override fun getException(): Exception? = exception
  }
}
