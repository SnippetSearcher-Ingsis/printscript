package visitor

import node.AssignationNode
import node.DeclarationNode
import node.DoubleExpressionNode
import node.ErrorNode
import node.IfElseNode
import node.LiteralNode
import node.PrintStatementNode
import node.ReadEnvNode
import node.ReadInputNode
import util.Context

internal interface VisitorStrategy {
  fun visit(context: Context, node: DoubleExpressionNode)

  fun visit(context: Context, node: LiteralNode<*>)

  fun visit(context: Context, node: PrintStatementNode)

  fun visit(context: Context, node: DeclarationNode)

  fun visit(context: Context, node: AssignationNode)

  fun visit(context: Context, node: ErrorNode)

  fun visit(context: Context, node: IfElseNode)

  fun visit(context: Context, node: ReadEnvNode)

  fun visit(context: Context, node: ReadInputNode)
}
