package com.printscript.models.node

class ReadInputNode(val expression: ASTNode, val position: Position) : ASTNode {
  override fun accept(visitor: ASTVisitor) {
    visitor.visit(this)
  }

  override fun toString(): String {
    return "ReadInputNode(value='$expression')"
  }
}
