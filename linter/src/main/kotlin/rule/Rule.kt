package rule

import node.ASTNode
import violation.Violation

sealed interface Rule {
  fun check(node: ASTNode): Violation?
}
