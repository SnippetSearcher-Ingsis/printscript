import rule.Casing
import rule.NoExpressionsInsidePrints
import rule.Rule

data class LinterConfig(val casing: String, val noExpressionsInsidePrints: Boolean) {
  val rules: List<Rule>
    get() = listOf(Casing(casing), NoExpressionsInsidePrints(noExpressionsInsidePrints))
}
