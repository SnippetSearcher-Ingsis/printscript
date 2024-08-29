package visitor

import node.AssignationNode
import node.DoubleExpressionNode
import node.LiteralNode
import node.PrintStatementNode
import node.VariableDeclarationNode
import util.Context
import util.Handler
import util.Solver

internal class EvaluationStrategy : VisitorStrategy {
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
}
