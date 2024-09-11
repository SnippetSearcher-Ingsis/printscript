package com.printscript.linter

import com.printscript.linter.violation.Violation

data class Evaluator(private val config: LinterConfig, private val violations: MutableList<Violation>) :
  com.printscript.models.tool.Tool {
  override fun evaluate(node: com.printscript.models.node.ASTNode) {
    for (rule in config.rules) {
      val violation = rule.check(node)
      if (violation != null) {
        violations.add(violation)
      }
    }
  }
}
