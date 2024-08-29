package rule

import node.ASTNode
import violation.Violation

sealed interface LintRule {
  fun check(node: ASTNode): Violation?
}
