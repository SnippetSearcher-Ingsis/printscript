import node.ASTNode
import rule.Rule
import violation.Violation

class Evaluator(private val rules: List<Rule>, private val violations: MutableList<Violation>) {
    fun evaluate(node: ASTNode) {
        for (rule in rules) {
            val violation = rule.check(node)
            if (violation != null) {
                violations.add(violation)
            }
        }
    }
}