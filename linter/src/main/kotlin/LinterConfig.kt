import rule.Casing
import rule.LintRule
import rule.NoExpressionsInsidePrints

data class LinterConfig(val casing: String, val noExpressionsInsidePrints: Boolean) {
  val rules: List<LintRule>
    get() = listOf(Casing(casing), NoExpressionsInsidePrints(noExpressionsInsidePrints))
}
