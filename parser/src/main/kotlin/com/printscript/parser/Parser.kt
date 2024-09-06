package com.printscript.parser

/**
 * Interface for the parser.
 */
interface Parser {
  fun parse(tokens: Iterator<List<com.printscript.models.token.Token>>): Iterator<com.printscript.models.node.ASTNode>
}
