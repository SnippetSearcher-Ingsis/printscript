package com.printscript.models.node

class ConstantNode(
  override val variable: String,
  override val variableType: String,
  override val expression: ASTNode,
  override val position: Position
) : DeclarationNode {
  override fun accept(visitor: ASTVisitor) {
    visitor.visit(this)
  }

  override fun toString(): String {
    return "ConstantNode(variable=$variable, variableType=$variableType, expression=$expression)"
  }
}
