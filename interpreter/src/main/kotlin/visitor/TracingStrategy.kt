package visitor

import node.AssignationNode
import node.DoubleExpressionNode
import node.ErrorNode
import node.LiteralNode
import node.PrintStatementNode
import node.VariableDeclarationNode
import node.VariableNode
import tracer.Tracer
import util.Context
import util.Solver

/**
 * Visitor that logs print statements
 */
internal class TracingStrategy(
  private val tracer: Tracer,
  private val print: Boolean,
) : VisitorStrategy {
  private val strategy = EvaluationStrategy

  override fun visit(context: Context, node: DoubleExpressionNode) {
    strategy.visit(context, node)
  }

  override fun visit(context: Context, node: LiteralNode<*>) {
    strategy.visit(context, node)
  }

  override fun visit(context: Context, node: PrintStatementNode) {
    val result = Solver.getValue(context, node.expression)
    tracer.print(result.toString())
    if (print) strategy.visit(context, node)
  }

  override fun visit(context: Context, node: VariableDeclarationNode) {
    strategy.visit(context, node)
  }

  override fun visit(context: Context, node: AssignationNode) {
    strategy.visit(context, node)
  }

  override fun visit(context: Context, node: ErrorNode) {
    strategy.visit(context, node)
  }

  override fun visit(context: Context, node: VariableNode) {
    strategy.visit(context, node)
  }
}
