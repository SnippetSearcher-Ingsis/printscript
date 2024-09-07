package com.printscript.parser

import com.printscript.models.catchable.Catchable
import com.printscript.models.node.ASTNode
import com.printscript.models.node.ErrorNode
import com.printscript.models.token.Token

class CatchableParser : Parser {

  override fun parse(tokens: Iterator<List<Token>>): CatchableParserIterator {
    return CatchableParserIterator(tokens)
  }

  inner class CatchableParserIterator(tokens: Iterator<List<Token>>) : Iterator<ASTNode>, Catchable {

    private val parser = PrintParser().parse(tokens)

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
