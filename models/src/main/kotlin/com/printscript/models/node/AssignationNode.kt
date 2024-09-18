package com.printscript.models.node

data class AssignationNode(
  val variable: String?,
  val expression: ASTNode,
  val position: Position
) : ASTNode {
  override fun toString(): String {
    return "$variable = $expression"
  }
}
