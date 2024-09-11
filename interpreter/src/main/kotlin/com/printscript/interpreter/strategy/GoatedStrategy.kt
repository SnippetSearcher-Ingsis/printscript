package com.printscript.interpreter.strategy

import com.printscript.interpreter.util.Handler
import com.printscript.interpreter.util.Services
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
internal class GoatedStrategy : Strategy {
  override fun visit(services: Services, node: ASTNode) {
    when (node) {
      is DoubleExpressionNode -> Solver.getValue(services, node)
      is LiteralNode<*> -> {}
      is PrintStatementNode -> services.output write (Solver.getValue(services, node.expression).toString())
      is DeclarationNode -> Handler.declareValue(services, node)
      is AssignationNode -> Handler.assignValue(services, node)
      is ErrorNode -> Handler.error(node)
      is ReadEnvNode -> {}
      is ReadInputNode -> {}
      is IfElseNode -> Handler.selectBranch(services, node)
    }
  }
}
