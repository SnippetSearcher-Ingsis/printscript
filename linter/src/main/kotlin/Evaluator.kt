import node.ASTNode
import tool.Tool
import violation.Violation

data class Evaluator(private val config: LinterConfig, private val violations: MutableList<Violation>) : Tool {
  override fun evaluate(node: ASTNode) {
    for (rule in config.rules) {
      val violation = rule.check(node)
      if (violation != null) {
        violations.add(violation)
      }
    }
  }
}
