package com.printscript.interpreter.strategy

import com.printscript.interpreter.OperationException
import com.printscript.interpreter.util.Services
import com.printscript.models.node.ASTNode
import com.printscript.models.node.IfElseNode

class IfElseStrategy : Strategy<IfElseNode> {
  override fun visit(services: Services, node: IfElseNode): Any? {
    val condition = services.visit(services, node.condition)
    when {
      condition !is Boolean -> throw OperationException("Condition must be a boolean.")
      condition -> handleBranch(services, node.ifBranch)
      else -> handleBranch(services, node.elseBranch)
    }
    return null
  }

  private fun handleBranch(services: Services, branch: List<ASTNode>) {
    val branchContext = services.context.clone()
    val branchServices = services.withContext(branchContext)
    branch.forEach { branchServices.visit(branchServices, it) }
    services.context replace branchContext
  }
}
