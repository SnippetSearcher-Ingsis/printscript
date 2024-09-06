package com.printscript.interpreter.visitor

import com.printscript.interpreter.tracer.Tracer
import com.printscript.interpreter.util.Context
import com.printscript.interpreter.util.Solver

/**
 * Visitor that logs print statements
 */
internal class TracingStrategy(
  private val tracer: Tracer,
  private val print: Boolean,
) : VisitorStrategy {
  private val strategy = EvaluationStrategy

  override fun visit(context: Context, node: com.printscript.models.node.DoubleExpressionNode) {
    strategy.visit(context, node)
  }

  override fun visit(context: Context, node: com.printscript.models.node.LiteralNode<*>) {
    strategy.visit(context, node)
  }

  override fun visit(context: Context, node: com.printscript.models.node.PrintStatementNode) {
    val result = Solver.getValue(context, node.expression)
    tracer.print(result.toString())
    if (print) strategy.visit(context, node)
  }

  override fun visit(context: Context, node: com.printscript.models.node.VariableDeclarationNode) {
    strategy.visit(context, node)
  }

  override fun visit(context: Context, node: com.printscript.models.node.AssignationNode) {
    strategy.visit(context, node)
  }

  override fun visit(context: Context, node: com.printscript.models.node.ErrorNode) {
    strategy.visit(context, node)
  }

  override fun visit(context: Context, node: com.printscript.models.node.VariableNode) {
    strategy.visit(context, node)
  }
}
