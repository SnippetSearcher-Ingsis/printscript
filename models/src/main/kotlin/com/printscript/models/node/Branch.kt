package com.printscript.models.node

class Branch(vararg val children: ASTNode) {

  override fun toString(): String {
    return children.joinToString(" | ")
  }
}
