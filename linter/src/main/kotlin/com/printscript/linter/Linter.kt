package com.printscript.linter

import com.printscript.linter.violation.Violation

class Linter(private val config: LinterConfig) {
  fun lint(node: com.printscript.models.node.ASTNode): List<Violation> {
    val result: MutableList<Violation> = mutableListOf()
    val evaluator = Evaluator(config, result)
    evaluator.evaluate(node)
    return result
  }
}
