package com.printscript.models.node

interface ASTVisitor {
  fun visit(node: DoubleExpressionNode)

  fun visit(node: LiteralNode<*>)

  fun visit(node: PrintStatementNode)

  fun visit(node: DeclarationNode)

  fun visit(node: AssignationNode)

  fun visit(node: ErrorNode)

  fun visit(node: IfElseNode)

  fun visit(node: ReadInputNode)

  fun visit(node: ReadEnvNode)
}
