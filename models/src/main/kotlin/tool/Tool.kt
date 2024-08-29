package tool

import node.ASTNode

interface Tool {
  fun evaluate(node: ASTNode)
}
