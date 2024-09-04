package node

interface ASTVisitor {
  fun visit(node: DoubleExpressionNode)

  fun visit(node: LiteralNode<*>)

  fun visit(node: PrintStatementNode)

  fun visit(node: VariableDeclarationNode)

  fun visit(node: AssignationNode)

  fun visit(node: ErrorNode)
}
