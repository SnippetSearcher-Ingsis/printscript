package com.printscript.models.tool

interface Tool {
  fun evaluate(node: com.printscript.models.node.ASTNode)
}
