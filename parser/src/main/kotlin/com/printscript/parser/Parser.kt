package com.printscript.parser

import com.printscript.parser.generator.ASTGenerator

class Parser : IParser {

  override fun parse(tokens: Iterator<List<com.printscript.models.token.Token>>): Iterator<com.printscript.models.node.ASTNode> {
    return ParserIterator(tokens)
  }

  inner class ParserIterator(private val tokens: Iterator<List<com.printscript.models.token.Token>>) : Iterator<com.printscript.models.node.ASTNode> {
    private val astGenerator = ASTGenerator()

    override fun hasNext(): Boolean {
      return tokens.hasNext()
    }

    override fun next(): com.printscript.models.node.ASTNode {
      if (!tokens.hasNext()) throw NoSuchElementException("No more AST nodes to parse.")
      return astGenerator.tokensToAST(tokens.next())
    }
  }
}
