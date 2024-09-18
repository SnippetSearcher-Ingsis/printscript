package com.printscript.models.node

class Branch(val children: List<ASTNode> = mutableListOf()) {

  fun add(node: ASTNode) {
    (children as MutableList).add(node)
  }

  override fun toString(): String {
    return children.joinToString(" | ")
  }
}
