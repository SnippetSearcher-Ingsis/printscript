package com.printscript.linter.rule

import com.printscript.linter.violation.ExpressionInsideReadInputViolation
import com.printscript.models.node.ASTNode
import com.printscript.models.node.DeclarationNode
import com.printscript.models.node.LiteralNode
import com.printscript.models.node.ReadInputNode

data class NoExpressionsInsideReadInputs(private val active: Boolean) : LintRule {
  override fun check(node: ASTNode): ExpressionInsideReadInputViolation? {
    return if (!active || node !is DeclarationNode) null else check(node)
  }

  private fun check(node: DeclarationNode): ExpressionInsideReadInputViolation? {
    return when (val expression = node.expression) {
      is ReadInputNode -> if (expression.expression !is LiteralNode<*>)
        ExpressionInsideReadInputViolation(node.position)
      else null
      else -> null
    }
  }
}
