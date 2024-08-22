package printScreen.parser

import node.ASTNode
import token.Token

/**
 * Interface for the parser.
 */
interface IParser {
  fun parse(tokens: List<Token>?): List<ASTNode>
}
