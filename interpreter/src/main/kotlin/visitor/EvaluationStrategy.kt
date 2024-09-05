package visitor

import node.AssignationNode
import node.DoubleExpressionNode
import node.ErrorNode
import node.LiteralNode
import node.PrintStatementNode
import node.VariableDeclarationNode
import node.VariableNode
import util.Context
import util.Handler
import util.Solver

internal data object EvaluationStrategy : VisitorStrategy {
  override fun visit(context: Context, node: DoubleExpressionNode) {
    Solver.getValue(context, node)
  }

  override fun visit(context: Context, node: LiteralNode<*>) {}

  override fun visit(context: Context, node: PrintStatementNode) {
    Handler.print(context, node.expression)
  }

  override fun visit(context: Context, node: VariableDeclarationNode) {
    Handler.declareValue(context, node)
  }

  override fun visit(context: Context, node: AssignationNode) {
    Handler.assignValue(context, node)
  }

  override fun visit(context: Context, node: ErrorNode) {
    throw Exception(node.error)
  }

  override fun visit(context: Context, node: VariableNode) {
    Handler.declareVariable(context, node)
  }
}
