package rule

import node.ASTNode
import node.LiteralNode
import node.ReadInputNode
import violation.ExpressionInsideReadInputViolation

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
