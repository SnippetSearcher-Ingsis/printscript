package com.printscript.models.node

class VariableNode(
  override val variable: String,
  override val variableType: String,
  override val expression: ASTNode,
  override val position: Position
) : DeclarationNode
