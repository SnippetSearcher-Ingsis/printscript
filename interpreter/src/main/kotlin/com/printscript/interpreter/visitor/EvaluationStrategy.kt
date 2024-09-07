package com.printscript.interpreter.visitor

import com.printscript.interpreter.util.Context
import com.printscript.interpreter.util.Handler
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
    Handler.error(node)
  }

  override fun visit(context: Context, node: ReadEnvNode) {
  }

  override fun visit(context: Context, node: ReadInputNode) {
  }

  override fun visit(context: Context, node: IfElseNode) {
    Handler.runBranch(context, node, this)
  }
}
