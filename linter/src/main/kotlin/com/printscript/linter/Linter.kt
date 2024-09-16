package com.printscript.linter

import com.printscript.linter.violation.Violation
import com.printscript.models.node.ErrorNode
import com.printscript.parser.CatchableParser.CatchableParserIterator

class Linter(private val config: LinterConfig) {
  fun lint(asts: CatchableParserIterator): List<Violation> {
    val result: MutableList<Violation> = mutableListOf()
    val evaluator = Evaluator(config, result)
    while (asts.hasNext()) {
      val node = asts.next()
      if (node is ErrorNode) break
      evaluator.evaluate(node)
    }
    return result
  }
}
