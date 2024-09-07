package com.printscript.linter.rule

import com.printscript.linter.violation.ExpressionInsideReadInputViolation
import com.printscript.models.node.ASTNode
import com.printscript.models.node.LiteralNode
import com.printscript.models.node.ReadInputNode

class NoExpressionsInsideReadInputs(private val active: Boolean) : LintRule {
  override fun check(node: ASTNode): ExpressionInsideReadInputViolation? {
    return if (!active || node !is ReadInputNode) {
      null
    } else {
      check(node)
    }
  }

  private fun check(node: ReadInputNode): ExpressionInsideReadInputViolation? {
    return if (node.expression !is LiteralNode<*>) {
      ExpressionInsideReadInputViolation(node.position)
    } else {
      null
    }
  }
}
