package com.printscript.models.node

class ConstantNode(
  override val variable: String,
  override val variableType: String,
  override val expression: ASTNode,
  override val position: Position
) : DeclarationNode
