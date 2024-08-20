package rule

import node.ASTNode
import violation.Violation

interface Rule {
    fun check(node: ASTNode): Violation?
}