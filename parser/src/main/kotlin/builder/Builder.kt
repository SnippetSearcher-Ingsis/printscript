package printScreen.parser.builder

import node.ASTNode

interface Builder {

  fun build(): ASTNode
}
