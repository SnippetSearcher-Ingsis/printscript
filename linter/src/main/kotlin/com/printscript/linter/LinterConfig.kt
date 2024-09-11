package com.printscript.linter

import com.google.gson.annotations.SerializedName
import com.printscript.linter.rule.Casing
import com.printscript.linter.rule.LintRule
import com.printscript.linter.rule.NoExpressionsInsidePrints
import com.printscript.linter.rule.NoExpressionsInsideReadInputs

data class LinterConfig(
  @SerializedName("identifier_format") val casing: String?,
  @SerializedName("mandatory-variable-or-literal-in-println") val noExpressionsInsidePrints: Boolean?,
  @SerializedName("mandatory-variable-or-literal-in-readInput") val noExpressionsInsideReadInputs: Boolean?
) {
  val rules: List<LintRule> get() =
    listOf(
      Casing(casing),
      NoExpressionsInsidePrints(noExpressionsInsidePrints ?: false),
      NoExpressionsInsideReadInputs(noExpressionsInsideReadInputs ?: false)
    )
}
