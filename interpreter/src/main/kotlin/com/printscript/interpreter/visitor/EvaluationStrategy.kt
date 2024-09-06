package com.printscript.interpreter.visitor

import com.printscript.interpreter.util.Context
import com.printscript.interpreter.util.Handler
import com.printscript.interpreter.util.Solver

internal data object EvaluationStrategy : VisitorStrategy {
  override fun visit(context: Context, node: com.printscript.models.node.DoubleExpressionNode) {
    Solver.getValue(context, node)
  }

  override fun visit(context: Context, node: com.printscript.models.node.LiteralNode<*>) {}

  override fun visit(context: Context, node: com.printscript.models.node.PrintStatementNode) {
    Handler.print(context, node.expression)
  }

  override fun visit(context: Context, node: com.printscript.models.node.VariableDeclarationNode) {
    Handler.declareValue(context, node)
  }

  override fun visit(context: Context, node: com.printscript.models.node.AssignationNode) {
    Handler.assignValue(context, node)
  }

  override fun visit(context: Context, node: com.printscript.models.node.ErrorNode) {
    Handler.error(node)
  }

  override fun visit(context: Context, node: com.printscript.models.node.VariableNode) {
    Handler.declareVariable(context, node)
  }
}
