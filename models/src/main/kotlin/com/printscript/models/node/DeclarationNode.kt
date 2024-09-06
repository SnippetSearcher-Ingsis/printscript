package com.printscript.models.node

interface DeclarationNode : ASTNode {
  val variable: String
  val variableType: String
  val expression: ASTNode
  val position: Position
  override fun accept(visitor: ASTVisitor)
}
