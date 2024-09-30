package com.printscript.interpreter.strategy

import com.printscript.interpreter.OperationException
import com.printscript.interpreter.util.Services
import com.printscript.models.node.ASTNode
import com.printscript.models.node.IfElseNode

val ifElseStrategy = Strategy<IfElseNode> { services, node ->
  val condition = services.visit(services, node.condition)
  when {
    condition !is Boolean -> throw OperationException("Condition must be a boolean.")
    condition -> handleBranch(services, node.ifBranch)
    else -> handleBranch(services, node.elseBranch)
  }
}

private val handleBranch = { services: Services, branch: List<ASTNode> ->
  val branchContext = services.context.clone()
  val branchServices = services.withContext(branchContext)
  branch.forEach { branchServices.visit(branchServices, it) }
  services.context replace branchContext
}
