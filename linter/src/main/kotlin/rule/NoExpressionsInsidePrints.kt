package rule

import node.ASTNode
import node.LiteralNode
import node.PrintStatementNode
import violation.ExpressionInsidePrintViolation
import violation.Violation

data class NoExpressionsInsidePrints(private val active: Boolean) : Rule {
  override fun check(node: ASTNode): Violation? {
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
