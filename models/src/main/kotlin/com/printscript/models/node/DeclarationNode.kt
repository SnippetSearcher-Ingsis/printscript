package com.printscript.models.node

sealed interface DeclarationNode : ASTNode {
  val identifier: String
  val valueType: String
  val expression: ASTNode
  val position: Position
}
