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

  override fun visit(context: Context, node: DeclarationNode) {
    strategy.visit(context, node)
  }

  override fun visit(context: Context, node: AssignationNode) {
    strategy.visit(context, node)
  }

  override fun visit(context: Context, node: ErrorNode) {
    strategy.visit(context, node)
  }

  override fun visit(context: Context, node: IfElseNode) {
    strategy.visit(context, node)
  }

  override fun visit(context: Context, node: ReadEnvNode) {
    strategy.visit(context, node)
  }

  override fun visit(context: Context, node: ReadInputNode) {
    strategy.visit(context, node)
  }
}
