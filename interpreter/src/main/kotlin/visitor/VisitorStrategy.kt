package visitor

import node.AssignationNode
import node.DoubleExpressionNode
import node.LiteralNode
import node.PrintStatementNode
import node.VariableDeclarationNode
import util.Context

internal interface VisitorStrategy {
  fun visit(context: Context, node: DoubleExpressionNode)

  fun visit(context: Context, node: LiteralNode<*>)

  fun visit(context: Context, node: PrintStatementNode)

  fun visit(context: Context, node: VariableDeclarationNode)

  fun visit(context: Context, node: AssignationNode)
}
