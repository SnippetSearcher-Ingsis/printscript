import rule.Casing
import rule.LintRule
import rule.NoExpressionsInsidePrints
import rule.NoExpressionsInsideReadInputs

data class LinterConfig(
  val casing: String,
  val noExpressionsInsidePrints: Boolean,
  val noExpressionsInsideReadInputs: Boolean
) {
  val rules: List<LintRule> get() =
    listOf(
      Casing(casing),
      NoExpressionsInsidePrints(noExpressionsInsidePrints),
      NoExpressionsInsideReadInputs(noExpressionsInsideReadInputs)
    )
}
