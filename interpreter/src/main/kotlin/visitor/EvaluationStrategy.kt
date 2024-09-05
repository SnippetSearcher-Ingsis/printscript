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
import util.Handler
import util.Solver

internal object EvaluationStrategy : VisitorStrategy {
  override fun visit(context: Context, node: DoubleExpressionNode) {
    Solver.getValue(context, node)
  }

  override fun visit(context: Context, node: LiteralNode<*>) {}

  override fun visit(context: Context, node: PrintStatementNode) {
    Handler.print(context, node.expression)
  }

  override fun visit(context: Context, node: DeclarationNode) {
    Handler.declareValue(context, node)
  }

  override fun visit(context: Context, node: AssignationNode) {
    Handler.assignValue(context, node)
  }

  override fun visit(context: Context, node: ErrorNode) {
    throw Exception(node.error)
  }

  override fun visit(context: Context, node: ReadEnvNode) {
  }

  override fun visit(context: Context, node: ReadInputNode) {
    Handler.waitForAnswer(context, node)
  }

  override fun visit(context: Context, node: IfElseNode) {
    Handler.runBranch(context, node)
  }
}
