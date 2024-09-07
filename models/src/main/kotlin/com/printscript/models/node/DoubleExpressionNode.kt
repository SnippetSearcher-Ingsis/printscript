package com.printscript.models.node

data class DoubleExpressionNode(val operator: String, val left: ASTNode, val right: ASTNode) :
  ASTNode
