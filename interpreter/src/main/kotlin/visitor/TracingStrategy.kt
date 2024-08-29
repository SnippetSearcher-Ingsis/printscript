package visitor

import logger.ILogger
import node.AssignationNode
import node.DoubleExpressionNode
import node.LiteralNode
import node.PrintStatementNode
import node.VariableDeclarationNode
import util.Context
import util.Solver

/**
 * Visitor that logs print statements
 */
internal class TracingStrategy(
  private val logger: ILogger,
  private val print: Boolean,
) : VisitorStrategy {
  private val strategy = EvaluationStrategy()

  override fun visit(context: Context, node: DoubleExpressionNode) {
    strategy.visit(context, node)
  }

  override fun visit(context: Context, node: LiteralNode<*>) {
    strategy.visit(context, node)
  }

  override fun visit(context: Context, node: PrintStatementNode) {
    val result = Solver.getValue(context, node.expression)
    logger.log(result.toString())
    if (print) strategy.visit(context, node)
  }

  override fun visit(context: Context, node: VariableDeclarationNode) {
    strategy.visit(context, node)
  }

  override fun visit(context: Context, node: AssignationNode) {
    strategy.visit(context, node)
  }
}
