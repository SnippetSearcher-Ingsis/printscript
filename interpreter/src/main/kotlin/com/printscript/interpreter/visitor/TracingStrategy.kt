package com.printscript.interpreter.visitor

import com.printscript.interpreter.tracer.Tracer
import com.printscript.interpreter.util.Context
import com.printscript.interpreter.util.Solver
import com.printscript.models.node.AssignationNode
import com.printscript.models.node.DeclarationNode
import com.printscript.models.node.DoubleExpressionNode
import com.printscript.models.node.ErrorNode
import com.printscript.models.node.IfElseNode
import com.printscript.models.node.LiteralNode
import com.printscript.models.node.PrintStatementNode
import com.printscript.models.node.ReadEnvNode
import com.printscript.models.node.ReadInputNode

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
