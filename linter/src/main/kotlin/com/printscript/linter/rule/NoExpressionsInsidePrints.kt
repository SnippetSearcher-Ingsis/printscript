package com.printscript.linter.rule

import com.printscript.linter.violation.ExpressionInsidePrintViolation
import com.printscript.models.node.ASTNode
import com.printscript.models.node.LiteralNode
import com.printscript.models.node.PrintStatementNode

class NoExpressionsInsidePrints(private val active: Boolean) : LintRule {
  override fun check(node: ASTNode): ExpressionInsidePrintViolation? {
    return if (!active || node !is PrintStatementNode) null else check(node)
  }

  private fun check(node: PrintStatementNode): ExpressionInsidePrintViolation? {
    return if (node.expression !is LiteralNode<*>) ExpressionInsidePrintViolation(node.position) else null
  }
}
