package printScreen.parser

import node.ASTNode
import token.Token

/**
 * Interface for the parser.
 */
interface IParser {
  fun parse(tokens: Iterator<List<Token>>): Iterator<ASTNode>
}
