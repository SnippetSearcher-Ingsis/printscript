package com.printscript.models.node

data class LiteralNode<T>(val value: T) : ASTNode {
  override fun toString(): String {
    return value.toString()
  }
}
