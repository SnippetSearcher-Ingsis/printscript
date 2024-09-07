package com.printscript.interpreter.strategy

import com.printscript.interpreter.UnknownNodeException
import com.printscript.interpreter.tracer.Tracer
import com.printscript.interpreter.util.Context
import com.printscript.interpreter.util.Handler
import com.printscript.interpreter.util.Solver
import com.printscript.models.node.ASTNode
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
) : Strategy {
  override fun visit(context: Context, node: ASTNode) {
    when (node) {
      is DoubleExpressionNode -> Solver.getValue(context, node)
      is LiteralNode<*> -> {}
      is PrintStatementNode -> tracer.print(Solver.getValue(context, node.expression).toString())
      is DeclarationNode -> Handler.declareValue(context, node)
      is AssignationNode -> Handler.assignValue(context, node)
      is ErrorNode -> Handler.error(node)
      is ReadEnvNode -> {}
      is ReadInputNode -> {}
      is IfElseNode -> Handler.runBranch(context, node, this)
      else -> throw UnknownNodeException(node::class.simpleName ?: "Unknown")
    }
  }
}
