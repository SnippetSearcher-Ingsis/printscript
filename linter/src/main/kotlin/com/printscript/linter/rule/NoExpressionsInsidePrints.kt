package com.printscript.linter.rule

import com.printscript.linter.violation.ExpressionInsidePrintViolation

data class NoExpressionsInsidePrints(private val active: Boolean) : LintRule {
  override fun check(node: com.printscript.models.node.ASTNode): ExpressionInsidePrintViolation? {
    if (!active || node !is com.printscript.models.node.PrintStatementNode) {
      return null
    }
    return check(node)
  }

  private fun check(node: com.printscript.models.node.PrintStatementNode): ExpressionInsidePrintViolation? {
    return if (node.expression !is com.printscript.models.node.LiteralNode<*>) {
      ExpressionInsidePrintViolation(node.position)
    } else {
      null
    }
  }
}
