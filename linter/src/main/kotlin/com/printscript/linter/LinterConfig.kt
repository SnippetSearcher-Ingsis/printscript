package com.printscript.linter

import com.printscript.linter.rule.Casing
import com.printscript.linter.rule.LintRule
import com.printscript.linter.rule.NoExpressionsInsidePrints

data class LinterConfig(val casing: String, val noExpressionsInsidePrints: Boolean) {
  val rules: List<LintRule>
    get() = listOf(Casing(casing), NoExpressionsInsidePrints(noExpressionsInsidePrints))
}
