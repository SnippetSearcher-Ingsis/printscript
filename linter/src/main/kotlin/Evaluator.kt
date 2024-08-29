import node.ASTNode
import violation.Violation

data class Evaluator(private val config: LinterConfig, private val violations: MutableList<Violation>) {
  fun evaluate(node: ASTNode) {
    for (rule in config.rules) {
      val violation = rule.check(node)
      if (violation != null) {
        violations.add(violation)
      }
    }
  }
}
