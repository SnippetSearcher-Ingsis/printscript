package com.printscript.linter

import com.printscript.linter.violation.Violation

data object Linter {
  fun lint(node: com.printscript.models.node.ASTNode, config: LinterConfig): List<Violation> {
    val result: MutableList<Violation> = mutableListOf()
    val evaluator = Evaluator(config, result)
    evaluator.evaluate(node)
    return result
  }
}
