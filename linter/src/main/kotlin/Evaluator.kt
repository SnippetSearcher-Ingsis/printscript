import node.ASTNode
import node.IfElseNode
import tool.Tool
import violation.Violation

data class Evaluator(private val config: LinterConfig, private val violations: MutableList<Violation>) : Tool {
  override fun evaluate(node: ASTNode) {
    if (node is IfElseNode) {
      node.ifBranch.forEach { evaluate(it) }
      node.elseBranch.forEach { evaluate(it) }
      return
    }
    for (rule in config.rules) {
      val violation = rule.check(node)
      if (violation != null) {
        violations.add(violation)
      }
    }
  }
}
