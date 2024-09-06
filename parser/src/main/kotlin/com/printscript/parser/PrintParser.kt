package com.printscript.parser

import com.printscript.models.node.ASTNode
import com.printscript.models.token.Token
import com.printscript.models.token.TokenType
import com.printscript.parser.generator.ASTGenerator

class PrintParser : Parser {

  override fun parse(tokens: Iterator<List<Token>>): Iterator<ASTNode> {
    return ParserIterator(tokens)
  }

  inner class ParserIterator(private val tokens: Iterator<List<Token>>) : Iterator<ASTNode> {
    private val astGenerator = ASTGenerator()

    override fun hasNext(): Boolean {
      return tokens.hasNext()
    }

    override fun next(): ASTNode {
      if (!tokens.hasNext()) throw NoSuchElementException("No more AST nodes to parse.")
      val token = tokens.next().toMutableList()
      if (token[0].type == TokenType.IF) {
        while (tokens.hasNext() && token.last().type != TokenType.CLOSE_BRACKET) {
          token += tokens.next()
        }
      }
      return astGenerator.tokensToAST(token)
    }
  }
}
