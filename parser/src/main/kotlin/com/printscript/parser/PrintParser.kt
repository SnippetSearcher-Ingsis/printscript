package com.printscript.parser

import com.printscript.models.node.ASTNode
import com.printscript.models.token.Token
import com.printscript.models.token.TokenType
import com.printscript.parser.generator.ASTGenerator

class PrintParser : Parser {

  override fun parse(tokens: Iterator<List<Token>>): ParserIterator {
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
        var nestingLevel = 1 // Start with 1 because we found an IF

        while (tokens.hasNext() && nestingLevel > 0) {
          val nextTokenList = tokens.next()
          token += nextTokenList

          // Check the first token of the list for nesting
          for (nextToken in nextTokenList) {
            if (nextToken.type == TokenType.OPEN_BRACKET) {
              nestingLevel++
            } else if (nextToken.type == TokenType.CLOSE_BRACKET) {
              nestingLevel--
            }
          }
        }
      }
      val result = astGenerator.createAST(token)
      return astGenerator.createAST(token)
    }
  }
}
