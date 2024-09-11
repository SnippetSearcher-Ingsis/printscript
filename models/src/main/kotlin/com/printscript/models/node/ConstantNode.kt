package com.printscript.models.node

class ConstantNode(
  override val identifier: String,
  override val valueType: String,
  override val expression: ASTNode,
  override val position: Position
) : DeclarationNode
