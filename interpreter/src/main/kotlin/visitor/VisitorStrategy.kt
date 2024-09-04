package visitor

import node.AssignationNode
import node.DoubleExpressionNode
import node.ErrorNode
import node.LiteralNode
import node.PrintStatementNode
import node.VariableDeclarationNode
import node.VariableNode
import util.Context

internal sealed interface VisitorStrategy {
  fun visit(context: Context, node: DoubleExpressionNode)

  fun visit(context: Context, node: LiteralNode<*>)

  fun visit(context: Context, node: PrintStatementNode)

  fun visit(context: Context, node: VariableDeclarationNode)

  fun visit(context: Context, node: AssignationNode)

  fun visit(context: Context, node: ErrorNode)

  fun visit(context: Context, node: VariableNode)
}
