package rule

import node.ASTNode
import node.LiteralNode
import node.PrintStatementNode
import violation.ExpressionInsidePrintViolation

data class NoExpressionsInsidePrints(private val active: Boolean) : LintRule {
  override fun check(node: ASTNode): ExpressionInsidePrintViolation? {
    if (!active || node !is PrintStatementNode) {
      return null
    }
    return check(node)
  }

  private fun check(node: PrintStatementNode): ExpressionInsidePrintViolation? {
    return if (node.expression !is LiteralNode<*>) {
      ExpressionInsidePrintViolation(node.position)
    } else {
      null
    }
  }
}
