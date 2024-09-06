package com.printscript.linter.rule

import com.printscript.linter.violation.Violation

sealed interface LintRule {
  fun check(node: com.printscript.models.node.ASTNode): Violation?
}
